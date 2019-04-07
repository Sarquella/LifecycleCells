package dev.sarquella.sample.ui.dialogs

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import dev.sarquella.sample.entities.ContactEntity
import dev.sarquella.sample.util.isNotEmptyOrError
import kotlinx.android.synthetic.main.contact_dialog.*

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

class EditContactDialogFragment : BaseContactDialogFragment() {

    private lateinit var viewModel: EditContactDialogViewModel

    companion object {

        private const val CONTACT_ID = "CONTACT_ID"

        fun newInstance(id: Int): EditContactDialogFragment {
            val dialogFragment = EditContactDialogFragment()
            dialogFragment.arguments = setArguments(id)
            return dialogFragment
        }

        private fun setArguments(id: Int): Bundle {
            val args = Bundle()
            args.putInt(CONTACT_ID, id)
            return args
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(EditContactDialogViewModel::class.java)
        arguments?.let {
            viewModel.init(it.getInt(CONTACT_ID))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fillContactInfo()
        setListeners()
    }

    private fun fillContactInfo() {
        viewModel.contactEntity.observe(this, Observer {
            etName.setText(it?.name)
            etSurname.setText(it?.surname)
            etNumber.setText(it?.number)
        })
    }

    private fun setListeners() {
        btPositive.setOnClickListener {
            saveContact()
        }
    }

    private fun saveContact() {
        if (etName.isNotEmptyOrError()
            && etSurname.isNotEmptyOrError()
            && etNumber.isNotEmptyOrError()) {

            viewModel.saveContact(
                ContactEntity(
                    etName.text.toString(),
                    etSurname.text.toString(),
                    etNumber.text.toString()))

            this.dismiss()
        }
    }
}