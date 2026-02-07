package com.pangea.horoscope.ui.horoscope.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pangea.horoscope.domain.model.HoroscopeInfo

class HoroscopeDiffCallback : DiffUtil.ItemCallback<HoroscopeInfo>() {
    override fun areItemsTheSame(
        oldItem: HoroscopeInfo,
        newItem: HoroscopeInfo
    ): Boolean {
        // Son objetos únicos (data object)
        return oldItem === newItem
    }

    override fun areContentsTheSame(
        oldItem: HoroscopeInfo,
        newItem: HoroscopeInfo
    ): Boolean {
        return oldItem == newItem
    }
}