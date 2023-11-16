package com.example.loginaplication.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.loginaplication.R

private const val WAIT_TIME: Long = 2000 //o WAIT_TIME = 2000L

class SplashFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onStart() {
        super.onStart()
        var r = Runnable {
            findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
        }
        Handler(Looper.getMainLooper()).postDelayed(r, WAIT_TIME)
    }
}