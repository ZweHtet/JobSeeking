package com.zwe.jobseekingproject.api

import com.zwe.jobseekingproject.model.JobsItem
import retrofit2.Call
import retrofit2.http.GET

interface CategoryApiInterface {
    @GET("api/setup/category")
    fun getCategory(): Call<JobsItem>
}