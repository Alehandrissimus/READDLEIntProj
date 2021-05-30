package com.intproj.contacts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.intproj.R
import com.intproj.contacts.data.models.PeopleContactModel
import com.intproj.databinding.ItemContactBinding
import com.intproj.databinding.ItemContactCompactBinding

class PeopleRVAdapter(
    private val callback: (PeopleContactModel) -> Unit,
    private val layoutManager: GridLayoutManager? = null
) : ListAdapter<PeopleContactModel, RecyclerView.ViewHolder>(PeopleDiffs()) {

    private enum class ViewType {
        STOCK,
        COMPACT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PeopleRVViewHolder -> holder.bind(getItem(position))
            is PeopleRVViewHolderCompact -> holder.bind(getItem(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.COMPACT.ordinal) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact_compact, parent, false)

            PeopleRVViewHolderCompact(view, callback)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contact, parent, false)

            PeopleRVViewHolder(view, callback)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.STOCK.ordinal
        else ViewType.COMPACT.ordinal
    }
}

class PeopleRVViewHolder(itemview: View, private val callback: (PeopleContactModel) -> Unit) :
    RecyclerView.ViewHolder(itemview) {

    private val binding = ItemContactBinding.bind(itemView)

    fun bind(itemModel: PeopleContactModel) {
        binding.apply {
            tvContactItem.text = itemModel.fullName
            Glide.with(itemView)
                .load(itemModel.avatar)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivContactItem)

            if (!itemModel.isOnline) ivContactItemStatus.visibility = View.GONE
        }

        binding.root.setOnClickListener {
            callback(itemModel)
        }
    }
}

class PeopleRVViewHolderCompact(
    itemview: View,
    private val callback: (PeopleContactModel) -> Unit
) :
    RecyclerView.ViewHolder(itemview) {

    private val binding = ItemContactCompactBinding.bind(itemView)

    fun bind(itemModel: PeopleContactModel) {
        binding.apply {
            Glide.with(itemView)
                .load(itemModel.avatar)
                .circleCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ivContactItemCompact)
            if (!itemModel.isOnline) ivContactItemCompactStatus.visibility = View.GONE
        }

        binding.root.setOnClickListener {
            callback(itemModel)
        }
    }
}

class PeopleDiffs : DiffUtil.ItemCallback<PeopleContactModel>() {
    override fun areItemsTheSame(
        oldItem: PeopleContactModel,
        newItem: PeopleContactModel
    ): Boolean {
        return oldItem.mail == newItem.mail
    }

    override fun areContentsTheSame(
        oldItem: PeopleContactModel,
        newItem: PeopleContactModel
    ): Boolean {
        return oldItem == newItem
    }

}