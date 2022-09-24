package Model

class Kambing(nama : String, jenis : String, usia : String) : Hewan(nama, jenis, usia) {

    override fun sound(): String{
        return "Mbekkk"
    }
}