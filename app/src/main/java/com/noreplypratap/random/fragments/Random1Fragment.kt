package com.noreplypratap.random.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.noreplypratap.random.databinding.FragmentRandom1Binding
import com.noreplypratap.random.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Random1Fragment : Fragment() {
    private lateinit var binding: FragmentRandom1Binding
    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandom1Binding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.getRandomNames()
        setObservers()
        binding.button1.setOnClickListener {
            mainViewModel.getRandomNames()
            //findNavController().navigate(R.id.action_random1Fragment_to_random2Fragment)
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setObservers() {
        mainViewModel.responseLiveData.observe(viewLifecycleOwner){
            Glide.with(this).load(it.avatar).circleCrop().into(binding.image1)
            binding.textView1.text = it.first_name + " " + it.last_name
            binding.textView2.text = it.date_of_birth
            binding.textView3.text = it.gender
            binding.textView4.text = it.email
        }
    }
}