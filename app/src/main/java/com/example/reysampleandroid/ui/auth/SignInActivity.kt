package com.example.reysampleandroid.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.reysampleandroid.databinding.ActivityMainBinding
import com.example.reysampleandroid.databinding.ActivitySignInBinding
import com.example.reysampleandroid.databinding.ActivitySignupBinding
import com.example.reysampleandroid.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActivity()
    }

    private fun setupActivity() {
        firebaseAuth = FirebaseAuth.getInstance()
        buttonClickListener()
        textViewClickListener()
    }

    private fun buttonClickListener() {
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInUser(email, password)
            } else {
                Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun textViewClickListener() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun signInUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }
    }
}