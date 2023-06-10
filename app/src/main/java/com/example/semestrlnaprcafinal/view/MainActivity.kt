package com.example.semestrlnaprcafinal.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.semestrlnaprcafinal.R


/**
 * Main activity používam ako kontajner pre
 * navigáciu medzi fragmentmi.
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setTitle("                  Weather by Vlad")

    }



}