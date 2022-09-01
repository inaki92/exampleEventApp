package com.example.todoappcat23.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyEvent(
    val name: String,
    val category: String,
    val date: String
) : Parcelable
