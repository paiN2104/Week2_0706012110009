package com.example.week2

import Database.GlobalVar
import Model.Ayam
import Model.Hewan
import Model.Kambing
import Model.Sapi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.week2.databinding.ActivityCreateBinding

class CreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateBinding
    private lateinit var tempHewan: Hewan
    var tempUri: String = ""
    private var position = -1
    private val GetResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data?.data
                binding.inputPoster.setImageURI(uri)
                if (uri != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        baseContext.getContentResolver().takePersistableUriPermission(
                            uri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        )
                    }
                    tempUri = uri.toString()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        GetIntent()
        listener()
    }
    private fun GetIntent(){
        position = intent.getIntExtra("position",-1)
        if(position!=-1){
            binding.AddHewanView.setText("Edit Hewan")
            binding.SubmitAddBut.setText("Save")
            val tempHewan = GlobalVar.listDataHewan[position]
            Display(tempHewan)
        }
    }
    private fun Display(tempHewan: Hewan?){
        binding.NamaInLayout.editText?.setText((tempHewan?.nama))
//        binding.JenisInputLayout.editText?.setText((tempHewan?.jenis))
        binding.UsiaInputLayout.editText?.setText((tempHewan?.usia))
        binding.inputPoster.setImageURI(Uri.parse(tempHewan?.imageUri))
        when(tempHewan?.jenis) {
            "Ayam" -> binding.ayamButton.setChecked(true)
            "Sapi" -> binding.sapiButton.setChecked(true)
            "Kambing" -> binding.kambingButton.setChecked(true)
        }

    }

    private fun listener() {
        binding.BackBut.setOnClickListener {
            val myIntent = Intent(this@CreateActivity, HomeActivity::class.java)
            startActivity(myIntent)
        }
        binding.SubmitAddBut.setOnClickListener{
            var nama = binding.NamaInLayout.editText?.text.toString().trim()
            var jenis =
                if(binding.jenisRadioGroup.checkedRadioButtonId==binding.ayamButton.id){
                    "Ayam"
                }else if(binding.jenisRadioGroup.checkedRadioButtonId==binding.sapiButton.id){
                    "Sapi"
                }else if(binding.jenisRadioGroup.checkedRadioButtonId==binding.kambingButton.id){
                    "Kambing"
                }else{
                    ""
                }

            var usia = binding.UsiaInputLayout.editText?.text.toString().trim()
//            binding.JenisInputLayout.editText?.text.toString().trim()
            if(jenis=="Ayam"){
                tempHewan=Ayam(nama,jenis,usia)
            }else if(jenis=="Sapi"){
                tempHewan=Sapi(nama, jenis, usia)
            }else if(jenis=="Kambing"){
                tempHewan=Kambing(nama, jenis, usia)
            }

//            tempHewan = Hewan(nama,jenis,usia)
            checker()
        }

        binding.inputPoster.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type="image/*"
            GetResult.launch(myIntent)
        }
    }
    private fun checker(){
        var isCompleted:Boolean = true
        if(tempHewan.nama!!.isEmpty()){
            binding.NamaInLayout.error = "Nama harus diisi"
            isCompleted = false
        }else{
            binding.NamaInLayout.error = ""
        }

        if(binding.jenisRadioGroup.checkedRadioButtonId ==-1){
            Toast.makeText(this, "Tolong Pilih Hewannya", Toast.LENGTH_SHORT).show()
            isCompleted=false
        }

        if(tempHewan.usia!!.isEmpty()) {
            binding.UsiaInputLayout.error = "Usia harus diisi"
            isCompleted = false
        }else if(tempHewan.usia!!.contains(".*[A-Za-z].*".toRegex())){
            binding.UsiaInputLayout.error = "Umur tidak boleh ada huruf"
            isCompleted=false
        } else if(tempHewan.usia!!.contains(".*[0-9].*".toRegex())){
            binding.UsiaInputLayout.error = ""
        }

        if(isCompleted){
            if(position==-1){
                tempHewan.imageUri= tempUri
                GlobalVar.listDataHewan.add(tempHewan)
                Toast.makeText(this, "Hewan Sukses Ditambahkan", Toast.LENGTH_SHORT).show()
                val myIntent = Intent(this,HomeActivity::class.java)
                startActivity(myIntent)
            }
            else{
                if(tempUri==GlobalVar.listDataHewan[position].imageUri) {
                    tempHewan.imageUri = GlobalVar.listDataHewan[position].imageUri
                }
                else if(tempUri==""){
                    tempHewan.imageUri = GlobalVar.listDataHewan[position].imageUri
                }
                else{
                    tempHewan.imageUri = tempUri
                }
                GlobalVar.listDataHewan[position]=tempHewan
                Toast.makeText(this, "Hewan Sukses Di Edit", Toast.LENGTH_SHORT).show()
                val myIntent = Intent(this,HomeActivity::class.java)
                startActivity(myIntent)
            }
            finish()
        }
    }
}
