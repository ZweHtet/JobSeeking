package com.zwe.jobseekingproject.model

import com.google.gson.annotations.SerializedName

data class Jobs(

	@field:SerializedName("jobs")
	val jobs: List<JobsItem?>? = null
)