package com.example.suitmedia.ui.third

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmedia.R
import com.example.suitmedia.adapter.LoadingStateAdapter
import com.example.suitmedia.adapter.UserListAdapter
import com.example.suitmedia.databinding.ActivityThirdBinding
import com.example.suitmedia.network.DataItem
import com.example.suitmedia.ui.second.SecondActivity

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private lateinit var name: String

    private val viewModel: ThirdViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Third Screen"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        name = intent.getStringExtra("NAME").toString()

        binding.rvListUser.layoutManager = LinearLayoutManager(this)
        loadData()
    }

    private fun loadData(){
        val adapter = UserListAdapter()
        binding.rvListUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                adapter.retry()
            }
        )
        viewModel.user.observe(this){
            adapter.submitData(lifecycle, it)
        }

        adapter.setOnItemClickCallback(object: UserListAdapter.OnItemClickCallback {
            override fun onItemCLicked(data: DataItem) {
                val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
                val fullName = data.firstName + " " + data.lastName
                intent.putExtra("DATA", fullName)
                intent.putExtra("NAME", name)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}