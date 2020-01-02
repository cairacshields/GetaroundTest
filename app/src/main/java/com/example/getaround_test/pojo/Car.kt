package com.example.getaround_test.pojo

import android.os.Parcel
import android.os.Parcelable

data class Car(
    val brand : String,
    val model : String,
    val picture_url: String,
    val price_per_day: Int,
    val rating: Rating,
    val owner: Owner
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        TODO("rating"),
        TODO("owner")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(brand)
        parcel.writeString(model)
        parcel.writeString(picture_url)
        parcel.writeInt(price_per_day)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }

        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }
    }
}