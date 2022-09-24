package Model

import android.os.Parcel
import android.os.Parcelable


    open class Hewan (
        var nama:String?,
        var jenis:String?,
        var usia:String?
    ):Parcelable {
        open fun sound(): String{
        return ""
        }
        fun feeding(grass: String): String{
        return "Kamu Memberi makanan berupa rumput"
        }
        fun feeding(seed: Int??): String{
            return "Kamu memberi makan berupa biji"
        }
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        var imageUri:String?=""

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(nama)
            parcel.writeString(jenis)
            parcel.writeString(usia)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Hewan> {
            override fun createFromParcel(parcel: Parcel): Hewan {
                return Hewan(parcel)
            }

            override fun newArray(size: Int): Array<Hewan?> {
                return arrayOfNulls(size)
            }
        }
    }

