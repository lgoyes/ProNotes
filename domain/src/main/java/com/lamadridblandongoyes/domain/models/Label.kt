package com.lamadridblandongoyes.domain.models

import android.os.Parcel
import android.os.Parcelable

data class Label (
    val labelId: Int? = null,
    val title: String,
    val color: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(labelId)
        parcel.writeString(title)
        parcel.writeString(color)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Label> {
        override fun createFromParcel(parcel: Parcel): Label {
            return Label(parcel)
        }

        override fun newArray(size: Int): Array<Label?> {
            return arrayOfNulls(size)
        }
    }
}

