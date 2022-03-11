package me.dio.simulator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FieldClassification
import com.bumptech.glide.Glide
import me.dio.simulator.databinding.ActivityDetail2Binding

class DetailActivity2 : AppCompatActivity() {

    object Extras {
        const val Match = "EXTRA_MATCH"
    }

    private lateinit var binding: ActivityDetail2Binding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindind = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let{
            Glide.with(this).load(it.place..image).into(binding.ivPlace)
            supportActionBar?title = it.place.name

            binding.tvDescription.text = it.description

            Glide.with(this).load(it.HomeTeam..image).into(binding.ivHomeTeam)
            binding.tvHomeTeamName.text = it.HomeTeam.name
            binding.rbHomeTeamStars.rating=it.homeTeam.stars.toFloat()
            if (it.homeTeam.score != null){
                binding.tvHomeTeamScore.text=it.homeTeam.score.toString()
            }

            Glide.with(this).load(it.AwayTeam..image).into(binding.ivAwayTeam)
            binding.tvAwayTeamName.text = it.AwayTeam.name
            binding.rbAwayTeamStars.rating=it.awayTeam.stars.toFloat()
            if (it.homeTeam.score != null){
                binding.tvAwayTeamScore.text=it.awayTeam.score.toString()
            }
        }
    }
}