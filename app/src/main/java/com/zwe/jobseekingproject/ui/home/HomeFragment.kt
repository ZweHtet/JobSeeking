package com.zwe.jobseekingproject.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zwe.jobseekingproject.R
import com.zwe.jobseekingproject.adapter.CategoryAdapter
import com.zwe.jobseekingproject.model.JobsItem
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("home", "home")
        viewManager = GridLayoutManager(activity, 2)
        categoryAdapter = CategoryAdapter()
        recyclerCategory.adapter = categoryAdapter
        recyclerCategory.layoutManager = viewManager

        observeViewModel()
    }

    fun observeViewModel() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeViewModel.getResults().observe(this, Observer<JobsItem> { result ->

            Log.d("viewModel", "viewModel")
            recyclerCategory.visibility = View.VISIBLE
            categoryAdapter.updateList(result.category)
        })

        homeViewModel.getError().observe(this, Observer<Boolean> { isError->
            if(isError){
                recyclerCategory.visibility = View.GONE
            }else{

            }
        })

        homeViewModel.getLoading().observe(this, Observer<Boolean> { isLoading ->
            loadingView.visibility = (if (isLoading) View.VISIBLE else View.INVISIBLE)
            if (isLoading) {
                recyclerCategory.visibility = View.GONE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.loadResults()
    }
}
