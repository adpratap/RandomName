package com.noreplypratap.random.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.noreplypratap.random.databinding.FragmentRandom2Binding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Random2Fragment : Fragment() {

    private lateinit var binding: FragmentRandom2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandom2Binding.inflate(inflater,container,false)
        //Glide.with(this).load(Constants.Image_URL).circleCrop().into(binding.image2)
        return binding.root
    }

}