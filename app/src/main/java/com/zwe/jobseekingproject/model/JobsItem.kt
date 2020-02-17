package com.zwe.jobseekingproject.model

import com.google.gson.annotations.SerializedName

data class JobsItem(

	@field:SerializedName("qualifications")
	val qualifications: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("details")
	val details: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("company")
	val company: Company? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("salary")
	val salary: String? = null,

	@field:SerializedName("experience")
	val experience: String? = null,

	@field:SerializedName("category")
	val category: Category? = null,

	@field:SerializedName("vacancy")
	val vacancy: String? = null
)