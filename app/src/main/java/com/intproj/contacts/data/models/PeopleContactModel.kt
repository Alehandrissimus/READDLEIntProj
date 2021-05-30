package com.intproj.contacts.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PeopleContactModel(
    val avatar: String,
    val fullName: String,
    val isOnline: Boolean,
    val mail: String,

) : Parcelable