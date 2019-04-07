package dev.sarquella.sample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.sarquella.R
import dev.sarquella.sample.singletons.EditMode
import dev.sarquella.sample.ui.dialogs.AddContactDialogFragment
import kotlinx.android.synthetic.main.main_activity.*

/*
 * Created by Adrià Sarquella on 16/08/2018.
 * adria@sarquella.dev
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        fabAddContact.setOnClickListener {
            AddContactDialogFragment().show(supportFragmentManager, "ADD_DIALOG")
        }

        swEditMode.setOnCheckedChangeListener { _, isChecked ->
            EditMode.value = isChecked
        }
    }
}
