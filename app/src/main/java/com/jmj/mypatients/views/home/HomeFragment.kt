package com.jmj.mypatients.views.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.jmj.mypatients.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_treatments_list.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_home, container, false)
        bindNavigation(binding)
        return binding
    }

    private fun bindNavigation(binding: View) {
        binding.initTreatmentButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_initTreatmentFragment)
        }
        binding.treatmentsButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_treatmentsFragment)
        }
    }


}
