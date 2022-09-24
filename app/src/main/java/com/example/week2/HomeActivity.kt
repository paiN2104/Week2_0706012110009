package com.example.week2

import Adapter.ListHewanRVAdapter
import Database.GlobalVar
import Interface.CardListener
import Model.Ayam
import Model.Hewan
import Model.Kambing
import Model.Sapi
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity(),CardListener{
    private lateinit var binding: ActivityHomeBinding
    private var adapter = ListHewanRVAdapter(GlobalVar.listDataHewan)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        listener()
        setupRecyclerView()
        //hidden()
    }
    private fun listener(){
        binding.addFAB.setOnClickListener{
            val myIntent = Intent(this@HomeActivity,CreateActivity::class.java)
            startActivity(myIntent)
        }


    }
    private fun hidden(){
        if(GlobalVar.listDataHewan.isNotEmpty()){
            binding.middletag.visibility = View.GONE
        }
        else
        {
            binding.middletag.visibility = View.VISIBLE
        }
    }
    override fun onResume() {
        super.onResume()
        GlobalVar.filterJenis.clear()
        binding.ayamFilter.setOnClickListener{
            GlobalVar.filterJenis.clear()
            for(item in GlobalVar.listDataHewan){
                if(item is Ayam){

                    GlobalVar.filterJenis.add(item)
                }
            }
            binding.listHewanRV.adapter = ListHewanRVAdapter(GlobalVar.filterJenis)
        }
        binding.sapiFilter.setOnClickListener{
            GlobalVar.filterJenis.clear()
            for(item in GlobalVar.listDataHewan){
                if(item is Sapi){
                    GlobalVar.filterJenis.add(item)
                }
            }
            binding.listHewanRV.adapter = ListHewanRVAdapter(GlobalVar.filterJenis)
        }
        binding.kambingFilter.setOnClickListener{
            GlobalVar.filterJenis.clear()
            for(item in GlobalVar.listDataHewan){
                if(item is Kambing){
                    GlobalVar.filterJenis.add(item)
                }
            }
            binding.listHewanRV.adapter = ListHewanRVAdapter(GlobalVar.filterJenis)
        }
        binding.resetFilter.setOnClickListener{
            GlobalVar.filterJenis.clear()
            binding.listHewanRV.adapter = ListHewanRVAdapter(GlobalVar.listDataHewan)
        }
        adapter.notifyDataSetChanged()
        if(GlobalVar.listDataHewan.isNotEmpty()){
            binding.middletag.visibility = View.GONE
        }
        else
        {
            binding.middletag.visibility = View.VISIBLE

        }



    }
    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        binding.listHewanRV.layoutManager= layoutManager //Set layout
        binding.listHewanRV.adapter=adapter //Set adapter
    }


}