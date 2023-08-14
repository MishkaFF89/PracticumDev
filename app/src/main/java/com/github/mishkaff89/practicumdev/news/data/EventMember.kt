package com.github.mishkaff89.practicumdev.news.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class EventMember(
    @SerializedName("id")
    val id: Int,
    @SerializedName("icon_src")
    val iconSrc: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(iconSrc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventMember> {
        override fun createFromParcel(parcel: Parcel): EventMember {
            return EventMember(parcel)
        }

        override fun newArray(size: Int): Array<EventMember?> {
            return arrayOfNulls(size)
        }
    }
}