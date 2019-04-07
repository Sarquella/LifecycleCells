package dev.sarquella.sample.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import dev.sarquella.sample.db.AppDb
import dev.sarquella.sample.entities.ContactEntity
import dev.sarquella.sample.util.CONTACTS_PAGE_SIZE

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

class ContactsListViewModel(app: Application) : AndroidViewModel(app) {

    private val contactsDao = AppDb.getInstance(app).getContactDao()

    val contactsList: LiveData<PagedList<ContactEntity>> =
        LivePagedListBuilder(contactsDao.contacts(), CONTACTS_PAGE_SIZE).build()
}