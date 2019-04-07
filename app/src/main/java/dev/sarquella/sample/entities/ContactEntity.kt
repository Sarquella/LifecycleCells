package dev.sarquella.sample.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

@Entity
data class ContactEntity(
    val name: String,
    val surname: String,
    val number: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
 
 