package com.jmj.mypatients.views.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.jmj.mypatients.R
import com.jmj.mypatients.databinding.FragmentTreatmentsListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class TreatmentsFragment : Fragment() {

    private val treatmentsViewModel: TreatmentsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTreatmentsListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_treatments_list, container, false)
        binding.viewModel = treatmentsViewModel
        bindList(binding)
        return binding.root
    }

    private fun bindList(binding: FragmentTreatmentsListBinding) {
        val adapter = TreatmentsAdapter(TreatmentListener(){
            treatmentId -> binding.root.findNavController().navigate(R.id.action_treatmentsList_to_treatmentFragment)
        })
        binding.treatmentsList.adapter = adapter
        treatmentsViewModel.myTreatments.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }

}
