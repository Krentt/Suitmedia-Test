package com.example.suitmedia.first

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.suitmedia.databinding.ActivityMainBinding
import com.example.suitmedia.second.SecondActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        binding.btCheck.setOnClickListener {
            val inputText = binding.edPalindrom.text.toString().trim()

            if (inputText.isEmpty()) {
                hideKeyboard()
                Toast.makeText(this, "Please enter a valid text!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.checkPalindrome(inputText)
                hideKeyboard()
            }
        }

        viewModel.text.observe(this) { text ->
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }

        binding.btNext.setOnClickListener {
            hideKeyboard()
            val name = binding.edName.text.toString().trim()

            if (name.isEmpty()) {
                hideKeyboard()
                Toast.makeText(this, "Please enter a valid name!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("NAME", name)
                startActivity(intent)
            }
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}