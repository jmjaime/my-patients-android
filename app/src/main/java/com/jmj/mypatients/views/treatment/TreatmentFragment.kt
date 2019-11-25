package com.jmj.mypatients.views.treatment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jmj.mypatients.R
import com.jmj.mypatients.databinding.FragmentTreatmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class TreatmentFragment : Fragment() {

    private val treatmentViewModel: TreatmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTreatmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_treatment, container, false)
        binding.viewModel = treatmentViewModel
        return binding.root
    }


}
