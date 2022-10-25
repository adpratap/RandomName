package com.noreplypratap.random.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.noreplypratap.random.R
import com.noreplypratap.random.databinding.FragmentRandom1Binding
import com.noreplypratap.random.utils.Constants
import com.noreplypratap.random.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Random1Fragment : Fragment() {
    private lateinit var binding: FragmentRandom1Binding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandom1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getRandomNames().invokeOnCompletion {
            binding.lmainviewmodel = mainViewModel
            binding.lifecycleOwner = this
        }
        loaddatafromnet()

        binding.button1.setOnClickListener {
            loaddatafromnet()
        }
    }

    private fun loaddatafromnet() {
        if (Constants.netStatus) {
            mainViewModel.getRandomNames()
        }else{
            findNavController().navigate(R.id.action_random1Fragment_to_random2Fragment)
        }
    }
}