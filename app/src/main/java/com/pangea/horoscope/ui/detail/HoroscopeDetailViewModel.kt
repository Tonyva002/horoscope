package com.pangea.horoscope.ui.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pangea.horoscope.R
import com.pangea.horoscope.domain.model.DomainError
import com.pangea.horoscope.domain.model.HoroscopeModel
import com.pangea.horoscope.domain.usecase.GetPredictionUseCase
import com.pangea.horoscope.ui.common.toUiMessageRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(
    private val getPredictionUseCase: GetPredictionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state

    lateinit var horoscope: HoroscopeModel

    fun getHoroscope(sign: HoroscopeModel) {
        horoscope = sign
        viewModelScope.launch {
            _state.value = HoroscopeDetailState.Loading
            try {
                val result = getPredictionUseCase(sign.name)
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign, horoscope)


            } catch (e: DomainError) {
                _state.value = HoroscopeDetailState.Error(e.toUiMessageRes())
            }
            catch (e: Exception) {
                _state.value = HoroscopeDetailState.Error(R.string.error)
            }
        }
    }
}