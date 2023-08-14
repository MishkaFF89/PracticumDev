package com.github.mishkaff89.practicumdev.news.data

import android.os.Parcel
import android.os.Parcelable

data class FilterCategory(
    val id: Int,
    val title: String?,
    var isChecked: Boolean
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeByte(if (isChecked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterCategory> {
        override fun createFromParcel(parcel: Parcel): FilterCategory {
            return FilterCategory(parcel)
        }

        override fun newArray(size: Int): Array<FilterCategory?> {
            return arrayOfNulls(size)
        }
    }
}