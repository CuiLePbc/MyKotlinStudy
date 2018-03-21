package com.cuile.mykotlinstudy.toutiao.data

import android.os.Parcel
import android.os.Parcelable


/**
 * Created by 崔乐 on 2017/5/25.
 *
 */
data class TouTiaoInfo(val result: TouTiaoInfoResult, val reason: String, val error_code: Int)

data class TouTiaoInfoResult(val stat: String, val data: Array<TouTiaoInfoResultData>)
data class TouTiaoInfoResultData(var date: String,
                                 var author_name: String,
                                 var thumbnail_pic_s: String,
                                 var uniquekey: String,
                                 var thumbnail_pic_s03: String,
                                 var thumbnail_pic_s02: String,
                                 var title: String,
                                 var category: String,
                                 var url: String) : Parcelable {
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

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<TouTiaoInfoResultData> = object: Parcelable.Creator<TouTiaoInfoResultData> {
            override fun createFromParcel(source: Parcel): TouTiaoInfoResultData {
                return TouTiaoInfoResultData(source)
            }

            override fun newArray(size: Int): Array<TouTiaoInfoResultData?> {
                return arrayOfNulls<TouTiaoInfoResultData>(size)
            }

        }
    }
}