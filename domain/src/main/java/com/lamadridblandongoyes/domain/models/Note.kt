package com.lamadridblandongoyes.domain.models

import android.os.Parcel
import android.os.Parcelable

data class Note (
    val noteId: Int?,
    val labelId: Int? = null,
    val title: String,
    val description: String,
    val reminder: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(noteId)
        parcel.writeValue(labelId)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(reminder)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }
}