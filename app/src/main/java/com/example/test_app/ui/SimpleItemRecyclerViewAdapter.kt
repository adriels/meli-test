package com.example.test_app.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test_app.R
import com.example.test_app.entity.QueryResult
import com.example.test_app.entity.Result
import kotlinx.android.synthetic.main.item_list_content.view.*

class SimpleItemRecyclerViewAdapter(var values: List<Result>, var primaryResults: Int) :
    RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Result
            v.context.startActivity(
                Intent(v.context, ItemDetailActivity::class.java)
                    .putExtra(ItemDetailFragment.ARG_ITEM_ID, item)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_content, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(values[position])
    }

    override fun getItemCount() = if (values.isNotEmpty() && values.size >= primaryResults) primaryResults else values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun onBind(item: Result) {
            with(itemView) {
                tag = item
                Glide.with(this)
                    .load(item.thumbnail)
                    .into(image)
                content.text = item.title
                price.text = "$ ${item.price}"
                setOnClickListener(onClickListener)
            }
        }
    }
}