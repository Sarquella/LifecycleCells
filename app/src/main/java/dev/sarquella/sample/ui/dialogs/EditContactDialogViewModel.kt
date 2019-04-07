package dev.sarquella.sample.ui.dialogs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.sarquella.sample.db.AppDb
import dev.sarquella.sample.entities.ContactEntity
import dev.sarquella.sample.util.ioThread

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

class EditContactDialogViewModel(app: Application) : AndroidViewModel(app) {

    private val contactsDao = AppDb.getInstance(app).getContactDao()

    var contactEntity: LiveData<ContactEntity> = MutableLiveData()
    private var contactId: Int = 0

    fun init(contactId: Int) {
        this.contactId = contactId
        ioThread {
            contactEntity = contactsDao.contact(contactId)
        }
    }

    fun saveContact(contactEntity: ContactEntity) {
        contactEntity.id = this.contactId
        ioThread {
            contactsDao.insert(contactEntity)
        }
    }
}