package com.example.suitmedia.ui.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.suitmedia.databinding.ActivitySecondBinding
import com.example.suitmedia.ui.third.ThirdActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Second Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("NAME")
        val selectedUser = intent.getStringExtra("DATA")

        if (selectedUser != null){
            binding.tvSelectedUser.text = selectedUser
        }

        binding.tvName.text = name

        binding.btChoose.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("NAME", name)
            startActivity(intent)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}