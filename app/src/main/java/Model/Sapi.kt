package Model

class Sapi(nama : String, jenis : String, usia : String) : Hewan(nama, jenis, usia) {

    override fun sound(): String{
        return "MOOOOOOOO"
    }
}