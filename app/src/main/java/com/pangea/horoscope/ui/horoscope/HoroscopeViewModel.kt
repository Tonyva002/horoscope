package com.pangea.horoscope.ui.horoscope

import androidx.lifecycle.ViewModel
import com.pangea.horoscope.data.providers.HoroscopeProvider
import com.pangea.horoscope.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider): ViewModel() {

    private var _uiState = MutableStateFlow<List<HoroscopeInfo>>(emptyList())

    val uiState: StateFlow<List<HoroscopeInfo>> = _uiState

    init {
        _uiState.value = horoscopeProvider.getHoroscopes()
    }
}