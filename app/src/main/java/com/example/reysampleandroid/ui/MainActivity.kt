package com.example.reysampleandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reysampleandroid.R
import com.example.reysampleandroid.databinding.ActivityMainBinding
import com.example.reysampleandroid.ui.auth.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupActivity()
    }

    override fun onStart() {
        super.onStart()
    }

    private fun setupActivity() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        updateUI()
    }

    private fun updateUI() {
        val user = firebaseAuth.currentUser
        binding.TextView.text = "Hello ${user?.email}"
    }
}