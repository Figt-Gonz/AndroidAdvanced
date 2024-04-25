package com.example.androidadvanced

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidadvanced.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.customView.animation = AnimationUtils.loadAnimation(this, R.anim.translate)
        ObjectAnimator.ofFloat(binding.customView, "translationX", 0f, 300f)
            .setDuration(1000)
            .start()

    }
}