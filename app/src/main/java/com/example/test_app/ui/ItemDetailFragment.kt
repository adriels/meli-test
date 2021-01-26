package com.example.test_app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.test_app.R
import com.example.test_app.entity.Result
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.*
import java.util.*

/**
 * A fragment representing a single Item detail screen.
 */
class ItemDetailFragment : Fragment() {

    private var item: Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the item specified by the fragment arguments.
                item = it[ARG_ITEM_ID] as Result
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        item?.let {
            activity?.toolbar_layout?.title = item?.title
            if (activity?.detail_image != null)
                Glide.with(this)
                    .load(item?.thumbnail)
                    .into(activity?.detail_image!!)
            item_condition.text = if (it.condition.isNullOrBlank()) "Condition not provided" else it.condition?.capitalize(Locale.getDefault())
            item_price.text = "${it.currency_id} ${it.price.toString()}"
            item_detail.text = "For more information visit:\n ${it.permalink}"
        }
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}