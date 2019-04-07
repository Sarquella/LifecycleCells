package dev.sarquella.sample.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import dev.sarquella.sample.entities.ContactEntity


/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: ContactEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contacts: List<ContactEntity>)

    @Delete
    fun delete(contact: ContactEntity)

    @Query("SELECT * FROM contactEntity WHERE id = :id")
    fun contact(id: Int): LiveData<ContactEntity>

    @Query("SELECT * FROM contactEntity ORDER BY surname ASC")
    fun contacts(): DataSource.Factory<Int, ContactEntity>
}

