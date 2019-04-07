package dev.sarquella.sample.ui

import androidx.recyclerview.widget.DiffUtil
import android.view.ViewGroup
import dev.sarquella.lifecyclecells.LifecyclePagedListAdapter
import dev.sarquella.sample.entities.ContactEntity

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

class ContactsListAdapter : LifecyclePagedListAdapter<ContactEntity, ContactCellViewHolder>(
    object : DiffUtil.ItemCallback<ContactEntity>() {
        override fun areItemsTheSame(
                oldItem: ContactEntity,
                newItem: ContactEntity): Boolean =
                oldItem.id == newItem.id

        override fun areContentsTheSame(
                oldItem: ContactEntity,
                newItem: ContactEntity): Boolean =
                oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactCellViewHolder =
            ContactCellViewHolder(parent)

    override fun onBindViewHolder(holder: ContactCellViewHolder, position: Int) =
            holder.bindTo(getItem(position))
}