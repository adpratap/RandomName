package com.noreplypratap.random.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.noreplypratap.random.R
import com.noreplypratap.random.databinding.FragmentRandom2Binding
import com.noreplypratap.random.network.NetworkManager
import com.noreplypratap.random.utils.Constants
import com.noreplypratap.random.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Random2Fragment : Fragment() {
    private lateinit var binding: FragmentRandom2Binding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandom2Binding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.retrybtn.setOnClickListener {
            mainViewModel.getRandomNames()
            if (Constants.netStatus){
                findNavController().navigate(R.id.action_random2Fragment_to_random1Fragment)
            }
        }
    }

}