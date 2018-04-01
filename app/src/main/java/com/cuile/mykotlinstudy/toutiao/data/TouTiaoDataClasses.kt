package com.cuile.mykotlinstudy.toutiao.data

import android.os.Parcel
import android.os.Parcelable
import com.cuile.mykotlinstudy.DataInterface
import com.cuile.mykotlinstudy.DataItemInterface


/**
 * Created by 崔乐 on 2017/5/25.
 *
 */
data class TouTiaoInfo(val result: TouTiaoInfoResult, val reason: String, val error_code: Int) : DataInterface

data class TouTiaoInfoResult(val stat: String, val data: List<TouTiaoInfoResultData>)
data class TouTiaoInfoResultData(val date: String,
                                 val author_name: String,
                                 val thumbnail_pic_s: String,
                                 val uniquekey: String,
                                 val thumbnail_pic_s03: String,
                                 val thumbnail_pic_s02: String,
                                 val title: String,
                                 val category: String,
                                 val url: String) : Parcelable, DataItemInterface {
    constructor(parcel: Parcel) : this(
            parcel.readString(), parcel.readString(), parcel.readString(),
            parcel.readString(), parcel.readString(), parcel.readString(),
            parcel.readString(), parcel.readString(), parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(date)
        dest.writeString(author_name)
        dest.writeString(thumbnail_pic_s)
        dest.writeString(uniquekey)
        dest.writeString(thumbnail_pic_s03)
        dest.writeString(thumbnail_pic_s02)
        dest.writeString(title)
        dest.writeString(category)
        dest.writeString(url)
    }
    override fun describeContents() = 0

    companion object CREATOR: Parcelable.Creator<TouTiaoInfoResultData> {
        override fun createFromParcel(source: Parcel): TouTiaoInfoResultData {
            return TouTiaoInfoResultData(source)
        }

        override fun newArray(size: Int): Array<TouTiaoInfoResultData?> {
            return arrayOfNulls(size)
        }
    }
}