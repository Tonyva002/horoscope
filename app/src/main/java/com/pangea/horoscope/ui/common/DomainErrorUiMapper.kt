package com.pangea.horoscope.ui.common

import com.pangea.horoscope.R
import com.pangea.horoscope.domain.model.DomainError

fun DomainError.toUiMessageRes(): Int = when (this) {
    is DomainError.Network -> R.string.not_network
    is DomainError.NotFound -> R.string.not_found_horoscope
    is DomainError.Server -> R.string.server_not_responding
    is DomainError.Unknown -> R.string.error
}