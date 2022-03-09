package me.dio.simulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.dio.simulator.databinding.ActivityDetail2Binding

class DetailActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityDetail2Binding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindind = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}