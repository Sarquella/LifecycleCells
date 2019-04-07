package dev.sarquella.sample.ui.dialogs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.sarquella.sample.db.AppDb
import dev.sarquella.sample.entities.ContactEntity
import dev.sarquella.sample.util.ioThread

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

class AddContactDialogViewModel(app: Application) : AndroidViewModel(app) {

    private val contactsDao = AppDb.getInstance(app).getContactDao()

    fun addContact(contactEntity: ContactEntity) {
        ioThread {
            contactsDao.insert(contactEntity)
        }
    }
}