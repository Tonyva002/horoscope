package com.pangea.horoscope.ui.detail

import androidx.annotation.StringRes
import com.pangea.horoscope.domain.model.HoroscopeModel

sealed class HoroscopeDetailState {
    data object Loading: HoroscopeDetailState()
    data class Error(@param:StringRes val errorRes: Int): HoroscopeDetailState()
    data class Success(val prediction: String, val sign: String, val horoscopeModel: HoroscopeModel): HoroscopeDetailState()
}