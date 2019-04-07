package dev.sarquella.sample.ui

import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import dev.sarquella.R
import dev.sarquella.lifecyclecells.LifecycleViewHolder
import dev.sarquella.sample.entities.ContactEntity
import dev.sarquella.sample.singletons.EditMode
import dev.sarquella.sample.ui.dialogs.EditContactDialogFragment
import kotlinx.android.synthetic.main.contact_cell.view.*

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

class ContactCellViewHolder(parent: ViewGroup) : LifecycleViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_cell, parent, false)) {

    fun bindTo(contactEntity: ContactEntity?) {
        contactEntity?.let {
            bindContact(it)
        }

        observeEditMode()
    }

    private fun bindContact(contact: ContactEntity) {
        itemView.tvName.text = "${contact.name} ${contact.surname}"
        itemView.tvNumber.text = contact.number

        itemView.btEdit.setOnClickListener {
            (itemView.context as? FragmentActivity)?.let { activity ->
                EditContactDialogFragment.newInstance(contact.id)
                    .show(activity.supportFragmentManager, "EDIT_DIALOG")
            }
        }
    }

    private fun observeEditMode() {
        EditMode.observe(this, Observer { isEditMode ->
            itemView.btEdit.visibility =
                if (isEditMode)
                    View.VISIBLE
                else
                    View.GONE
        })
    }
}