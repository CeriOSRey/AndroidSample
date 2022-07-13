package com.example.reysampleandroid.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.reysampleandroid.databinding.ActivitySignupBinding
import com.example.reysampleandroid.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivity()
    }

    private fun setupActivity() {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        buttonClickListener()
        textViewClickListener()
    }

    private fun buttonClickListener() {
        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val password = binding.passET.text.toString()
            val confirmPass = binding.confirmPassET.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (password == confirmPass) {
                    createUserInFirebase(email, password)
                } else {
                    Toast.makeText(this, "Passwords are not matching.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun textViewClickListener() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun createUserInFirebase(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { taskResult ->
            if (taskResult.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
            } else {
                Toast.makeText(this, taskResult.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}