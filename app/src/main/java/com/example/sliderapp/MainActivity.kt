package com.example.sliderapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.material.slider.Slider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    lateinit var slider: SeekBar
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        slider = findViewById(R.id.slider)
        sharedPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        slider.progress = sharedPrefs.getInt("lastValue", 0)

        slider.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val contextView = findViewById<View>(R.id.contextView)

                    Snackbar.make(contextView, "Val is $progress", Snackbar.LENGTH_SHORT)
                        .show()

                    val prefEditor = sharedPrefs.edit()
                    prefEditor.putInt("lastValue", progress)
                    prefEditor.commit()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) { }

                override fun onStopTrackingTouch(seekBar: SeekBar?) { }
            }
        )
    }
}