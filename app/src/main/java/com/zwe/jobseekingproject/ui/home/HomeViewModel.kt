package com.zwe.jobseekingproject.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zwe.jobseekingproject.api.CategoryApi
import com.zwe.jobseekingproject.model.JobsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var results: MutableLiveData<JobsItem> = MutableLiveData()

    var loading: MutableLiveData<Boolean> = MutableLiveData()


    var categoryLoadError: MutableLiveData<Boolean> = MutableLiveData()

    fun getError(): LiveData<Boolean> = categoryLoadError

    fun getResults(): LiveData<JobsItem> = results

    fun getLoading(): LiveData<Boolean> = loading

    private val categoryApi: CategoryApi = CategoryApi()

    fun loadResults() {
        loading.value = true
        val call = categoryApi.getResult()
        call.enqueue(object : Callback<JobsItem> {
            override fun onFailure(call: Call<JobsItem>, t: Throwable) {
                loading.value = false
                categoryLoadError.value = true
            }

            override fun onResponse(call: Call<JobsItem>, response: Response<JobsItem>) {
                response?.isSuccessful.let {
                    loading.value = false
                    val resultList = response.body()
                    Log.d("resultList", resultList.toString())
                    results.value = resultList
                }
            }


        })
    }


}