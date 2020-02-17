package com.zwe.jobseekingproject.ui.login


import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

import com.zwe.jobseekingproject.R
import com.zwe.jobseekingproject.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    private var email: String? = null

    private var loginPassword: String? = null

    private var mAuth: FirebaseAuth? = null

    private var mProgress: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_login, container, false)

        var signUp = root.btnSignup
        signUp.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }



        var forgotPassword = root.btnForgotPassword
        forgotPassword.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)

        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLogin()
    }

    private fun initLogin(){
        mProgress = ProgressDialog(activity)
        mAuth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {view : View->
            loginUser()
            //view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment )

        }
    }

    private fun loginUser(){
        email =  username.text.toString()
        loginPassword = password.text.toString()

        mProgress!!.setMessage("login processing...")
        mProgress!!.show()

        mAuth!!.signInWithEmailAndPassword(email!!, loginPassword!!).addOnCompleteListener(activity!!, OnCompleteListener { task ->

            if (task.isSuccessful){
                mProgress!!.hide()
                view!!.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

            }else {
                Toast.makeText(activity, "Login Failed", Toast.LENGTH_LONG).show()
            }
        })
    }


}
