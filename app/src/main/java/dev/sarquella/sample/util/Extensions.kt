package dev.sarquella.sample.util

import android.widget.EditText
import dev.sarquella.R


/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

fun EditText.isNotEmptyOrError(
    error: String = context.getString(R.string.Empty_field_error)): Boolean {
    if (this.text.isEmpty()) {
        this.error = error
        return false
    }
    return true
}
 
 