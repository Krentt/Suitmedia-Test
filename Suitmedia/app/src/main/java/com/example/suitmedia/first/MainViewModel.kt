package com.example.suitmedia.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    fun checkPalindrome(text: String): Boolean {
        val textTrimmed = text.replace(" ", "")
        val isPalindrome = textTrimmed == textTrimmed.reversed()
        _text.value = if (isPalindrome) "isPalindrome" else "Not Palindrome"
        return isPalindrome
    }
}