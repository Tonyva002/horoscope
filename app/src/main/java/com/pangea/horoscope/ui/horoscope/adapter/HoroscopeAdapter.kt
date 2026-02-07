package com.pangea.horoscope.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.pangea.horoscope.R
import com.pangea.horoscope.domain.model.HoroscopeInfo

class HoroscopeAdapter(
    private val onItemSelected: (HoroscopeInfo) -> Unit
): ListAdapter<HoroscopeInfo, HoroscopeViewHolder>(HoroscopeDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HoroscopeViewHolder {
        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: HoroscopeViewHolder,
        position: Int
    ) {
        holder.render(getItem(position), onItemSelected)
    }
}