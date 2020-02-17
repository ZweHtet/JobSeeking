package com.zwe.jobseekingproject.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zwe.jobseekingproject.R
import com.zwe.jobseekingproject.model.Category
import kotlinx.android.synthetic.main.home_category.view.*

class CategoryAdapter(var categoryList: List<Category> = ArrayList()) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var view: View = itemView
        private lateinit var category: Category

        fun bindCategory(category: Category) {
            this.category = category

            Log.d("category", category.name.toString())
            view.jobCategory.text = category.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(categoryList[position])
    }

    fun updateList(categoryList: Category?) {
        this.categoryList = listOf(categoryList!!)
        notifyDataSetChanged()
    }
}

