package com.dogancan.core.extensions

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.ProgressBar

/**
 * @author dogancankilic
 * Created on 6.08.2022
 */
fun Context.progressDialog(): Dialog {
    val dialog = Dialog(this)
    val progressBar = ProgressBar(this)
    progressBar.isIndeterminate = true
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(progressBar)
    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    dialog.setCanceledOnTouchOutside(false)
    dialog.setCancelable(false)
    return dialog
}
