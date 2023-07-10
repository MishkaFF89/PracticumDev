package com.github.mishkaff89.practicumdev

import android.app.AlertDialog
import android.content.Context
import android.widget.LinearLayout

class ProfileAlertDialog(context: Context): AlertDialog(context) {

    private var dialogView = layoutInflater.inflate(R.layout.profile_alert_dialog, null)

    private var alertDialog: AlertDialog? = null

    init {
        val builder = Builder(context)
        builder.setView(dialogView)
        alertDialog = builder.create()
    }

    fun showDialog() {
        alertDialog?.show()
    }

    private var onClickItem: ((Int) -> Unit)? = null

    private val optionUpload by lazy {
        dialogView.findViewById<LinearLayout>(R.id.click_upload)
    }

    private val optionTakePhoto by lazy {
        dialogView.findViewById<LinearLayout>(R.id.click_take_photo)
    }

    private val optionDelete by lazy {
        dialogView.findViewById<LinearLayout>(R.id.click_delete)
    }

    fun onItemClick(action: (Int) -> Unit) {
        onClickItem = action
        setListeners()
    }

    private fun setListeners() {
        optionUpload.setOnClickListener { onClickItem?.invoke(R.id.click_upload) }
        optionTakePhoto.setOnClickListener { onClickItem?.invoke(R.id.click_take_photo) }
        optionDelete.setOnClickListener { onClickItem?.invoke(R.id.click_delete) }
    }

}