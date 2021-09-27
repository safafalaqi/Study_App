package com.example.studyapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

class AlertDialog(val context:Context) {

    fun customAlert(title:String, detail:String )
    {
            // first we create a variable to hold an AlertDialog builder
            val dialogBuilder = AlertDialog.Builder(context)
            // here we set the message of our alert dialog
            dialogBuilder.setMessage(detail)
                // negative button text and action
                .setNegativeButton("Ok", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                })
            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle(title)
            // show alert dialog
            alert.show()
    }
}