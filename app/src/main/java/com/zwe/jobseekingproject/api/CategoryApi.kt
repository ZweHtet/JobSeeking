package com.zwe.jobseekingproject.api

import com.zwe.jobseekingproject.model.JobsItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryApi {
    private val categoryApiInterface: CategoryApiInterface

    companion object {
        const val Base_URL = "http://job-agency.khaingthinkyi.me/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        categoryApiInterface = retrofit.create(CategoryApiInterface::class.java)
    }

    fun getResult(): Call<JobsItem> {
        return categoryApiInterface.getCategory()
    }
}