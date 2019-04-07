package dev.sarquella.sample.ui.dialogs

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import dev.sarquella.sample.entities.ContactEntity
import dev.sarquella.sample.util.isNotEmptyOrError
import kotlinx.android.synthetic.main.contact_dialog.*

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

class AddContactDialogFragment : BaseContactDialogFragment() {

    private lateinit var viewModel: AddContactDialogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AddContactDialogViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        btPositive.setOnClickListener {
            addContact()
        }
    }

    private fun addContact() {
        if (etName.isNotEmptyOrError()
            && etSurname.isNotEmptyOrError()
            && etNumber.isNotEmptyOrError()) {

            viewModel.addContact(
                ContactEntity(
                    etName.text.toString(),
                    etSurname.text.toString(),
                    etNumber.text.toString()))

            this.dismiss()
        }
    }
}