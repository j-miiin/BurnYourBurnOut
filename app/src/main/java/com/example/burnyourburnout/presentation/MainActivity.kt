package com.example.burnyourburnout.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.burnyourburnout.R
import com.example.burnyourburnout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)
    }
}