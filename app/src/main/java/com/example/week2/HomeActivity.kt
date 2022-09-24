package com.example.week2

import Adapter.ListHewanRVAdapter
import Database.GlobalVar
import Interface.CardListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week2.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity(),CardListener{
    private lateinit var binding: ActivityHomeBinding
    private var adapter = ListHewanRVAdapter(GlobalVar.listDataHewan,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupRecyclerView()
        listener()
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