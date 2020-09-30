package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import personal.nfl.permission.support.util.AbcPermission

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AbcPermission.install(this)
        readFile()
    }

    private fun readFile() {
        Toast.makeText(this, "readFile", Toast.LENGTH_SHORT).show()
    }
}