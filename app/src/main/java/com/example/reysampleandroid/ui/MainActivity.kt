package com.example.reysampleandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.reysampleandroid.databinding.ActivityMainBinding
import com.example.reysampleandroid.model.Joke
import com.example.reysampleandroid.util.DataState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupActivity()
        viewModel.setStateEvent(MainStateEvent.GetJokeEvents)
    }

    override fun onStart() {
        super.onStart()
    }

    private fun setupActivity() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        subscribeObservers()
    }

    private fun updateUI(joke: String) {

        binding.TextView.text = "$joke"
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<Joke> -> {
                    displayJokes(dataState.data)
                }
                is DataState.Error -> {
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    // can display progress bar here if need to
                }
            }
        })
    }

    private fun displayError(message: String?) {
        if (message != null) {
            println("$TAG: $message")
            Toast.makeText(this, "$TAG: $message", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "$TAG: unknown error.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayJokes(joke: Joke) {
        val sb = StringBuilder()

        val sbJoke = sb.append("Dad Joke: " + joke.body).toString()
        updateUI(sbJoke)


    }
}