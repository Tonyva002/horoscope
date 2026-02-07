package com.pangea.horoscope.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.pangea.horoscope.R
import com.pangea.horoscope.databinding.ActivityHoroscopeDetailBinding
import com.pangea.horoscope.domain.model.HoroscopeModel
import com.pangea.horoscope.domain.model.HoroscopeModel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHoroscopeDetailBinding
    private  val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initUI()
        horoscopeDetailViewModel.getHoroscope(args.type)
    }

    private fun initUI() {
        initListeners()
        initUIState()
    }

    private fun initListeners() {
binding.ivBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                horoscopeDetailViewModel.state.collect {
                    when(it){
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Success -> successState(it)
                        is HoroscopeDetailState.Error -> errorState()

                    }
                }
            }
        }
    }

    private fun loadingState(){
        binding.progressBar.isVisible = true

    }

    private fun successState(state: HoroscopeDetailState.Success){
        binding.progressBar.isVisible = false
        binding.tvTitle.text = state.sign
        binding.tvBody.text = state.prediction

        val image = when(state.horoscopeModel){
            Aries -> R.drawable.aries
            Taurus -> R.drawable.tauro
            Gemini -> R.drawable.geminis
            Cancer -> R.drawable.cancer
            Leo -> R.drawable.leo
            Virgo -> R.drawable.virgo
            Libra -> R.drawable.libra
            Scorpio -> R.drawable.escorpio
            Sagittarius -> R.drawable.sagitario
            Capricorn -> R.drawable.capricornio
            Aquarius -> R.drawable.aquario
            Pisces -> R.drawable.piscis
        }
        binding.ivDetail.setImageResource(image)

    }

    private fun errorState(){
        binding.progressBar.isVisible = false

    }
}