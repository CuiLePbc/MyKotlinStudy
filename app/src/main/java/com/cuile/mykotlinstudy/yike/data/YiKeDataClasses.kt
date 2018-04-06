package com.cuile.mykotlinstudy.yike.data

import android.os.Parcel
import android.os.Parcelable
import com.cuile.mykotlinstudy.intfac.DataInterface
import com.cuile.mykotlinstudy.intfac.DataItemInterface


data class YiKeInfo(
		val reason: String,
		val result: List<YiKeInfoResultData>,
		val error_code: Int
) : DataInterface

data class YiKeInfoResultData(
		val content: String,
		val hashId: String,
		val unixtime: Int,
		var url: String?
) : Parcelable, DataItemInterface {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(content)
        parcel.writeString(hashId)
        parcel.writeInt(unixtime)
        parcel.writeString(url)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<YiKeInfoResultData> {
        override fun createFromParcel(parcel: Parcel): YiKeInfoResultData {
            return YiKeInfoResultData(parcel)
        }

        override fun newArray(size: Int): Array<YiKeInfoResultData?> {
            return arrayOfNulls(size)
        }
    }

}