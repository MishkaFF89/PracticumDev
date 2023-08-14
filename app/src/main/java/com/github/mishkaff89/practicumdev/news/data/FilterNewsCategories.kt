package com.github.mishkaff89.practicumdev.news.data

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

class FilterNewsCategories(val categories: ArrayList<FilterCategory>?): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(FilterCategory)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(categories)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FilterNewsCategories> {
        override fun createFromParcel(parcel: Parcel): FilterNewsCategories {
            return FilterNewsCategories(parcel)
        }

        override fun newArray(size: Int): Array<FilterNewsCategories?> {
            return arrayOfNulls(size)
        }
    }
}