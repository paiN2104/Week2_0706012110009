package Model

import android.widget.Toast

class Ayam(nama : String, jenis : String, usia : String) : Hewan(nama,jenis,usia) {

    override fun sound(): String{

        return "Kukuruyuk"
    }
}