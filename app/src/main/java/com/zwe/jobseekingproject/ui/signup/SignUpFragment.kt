package com.zwe.jobseekingproject.ui.signup


import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.zwe.jobseekingproject.MainActivity
import com.zwe.jobseekingproject.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

/**
 * A simple [Fragment] subclass.
 */
class SignUpFragment : Fragment() {

    private var mAuth: FirebaseAuth? = null

    private var mDatabase: FirebaseDatabase? = null

    private var mDatabaseReference: DatabaseReference? = null

    private var mProgressBar: ProgressDialog? = null

    private var username: String? = null

    private var email: String? = null

    private var password: String? = null

    private var confirmpassword: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_sign_up, container, false)

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFirebase()
    }

    private fun initFirebase() {

        mProgressBar = ProgressDialog(activity)
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference!!.child("Users")     //"Users is table name"

        mAuth = FirebaseAuth.getInstance()

        btnRealSignup.setOnClickListener {
            username = inputUsername.text.toString()
            email = inputEmail.text.toString()
            password = inputPassword.text.toString()
            confirmpassword = inputConfirmPassword.text.toString()

            createAccount()
        }
    }

    private fun createAccount() {
        mProgressBar!!.setMessage("SignUp Progressing......")
        mProgressBar!!.show()

        mAuth!!.createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(activity!!, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        activity, "Success SignUp", Toast.LENGTH_LONG
                    ).show()
                    val email = mAuth!!.currentUser!!.uid

                    verifyEmail()
                    val currentUserDB = mDatabaseReference!!.child(email)
                    currentUserDB.child("username").setValue(username)
                    currentUserDB.child("email").setValue(email)
                    updateUI()
                } else {
                    Toast.makeText(
                        activity, "Failed SignUp", Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    private fun verifyEmail() {
        val mUser = mAuth!!.currentUser
        mUser!!.sendEmailVerification()
            .addOnCompleteListener(activity!!, OnCompleteListener<Void> { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Verification Successful", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun updateUI() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

}
