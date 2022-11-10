package com.example.usbank.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class AlbumsDetailsModel() : Parcelable {

    var url: String? = null
    var albumId: String? = null
    var id: String? = null
    var title: String? = null
    var thumbnailUrl: String? = null

    constructor(parcel: Parcel) : this() {
        url = parcel.readString()
        albumId = parcel.readString()
        id = parcel.readString()
        title = parcel.readString()
        thumbnailUrl = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(albumId)
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(thumbnailUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlbumsDetailsModel> {
        override fun createFromParcel(parcel: Parcel): AlbumsDetailsModel {
            return AlbumsDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<AlbumsDetailsModel?> {
            return arrayOfNulls(size)
        }
    }

}