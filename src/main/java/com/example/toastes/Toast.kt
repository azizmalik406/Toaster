package com.example.toastes

import android.content.Context
import android.widget.Toast

object Toast {
    fun ShowToast(context: Context,message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}