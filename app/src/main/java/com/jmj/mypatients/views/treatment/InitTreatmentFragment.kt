package com.jmj.mypatients.views.treatment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jmj.mypatients.R
import com.jmj.mypatients.databinding.FragmentInitTreatmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class InitTreatmentFragment : Fragment() {

    private val initTreatmentViewModel: InitTreatmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentInitTreatmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_init_treatment, container, false)
        binding.viewModel = initTreatmentViewModel
        listenViewModelStatus()
        initPatientSources(binding)
        initAvailableOffices(binding)
        return binding.root
    }

    private fun initAvailableOffices(binding: FragmentInitTreatmentBinding) {
        initTreatmentViewModel.availableOffices.observe(viewLifecycleOwner, Observer {
            val officeNames: List<String> = it.map { ps -> ps.name }.toMutableList()
            ArrayAdapter<String>(
                activity,
                R.layout.support_simple_spinner_dropdown_item,
                officeNames
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.availableOffices.adapter = adapter
                binding.availableOffices.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            initTreatmentViewModel.selectedOffice.value = null
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            initTreatmentViewModel.availableOffices.value?.getOrNull(position)
                                ?.let { office ->
                                    initTreatmentViewModel.selectedOffice.value = office
                                }
                        }

                    }
            }
        })
    }

    private fun initPatientSources(binding: FragmentInitTreatmentBinding) {
        initTreatmentViewModel.availablePatientSources.observe(viewLifecycleOwner, Observer {
            val sourceNames: List<String> = it.map { ps -> ps.source }.toMutableList()
            ArrayAdapter<String>(
                activity,
                R.layout.support_simple_spinner_dropdown_item,
                sourceNames
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.availablePatientSources.adapter = adapter
                binding.availablePatientSources.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            initTreatmentViewModel.selectedPatientSource.value = null
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            initTreatmentViewModel.availablePatientSources.value?.getOrNull(position)
                                ?.let { patientSource ->
                                    initTreatmentViewModel.selectedPatientSource.value =
                                        patientSource
                                }
                        }
                    }
            }
        })
    }


    private fun listenViewModelStatus() {
        initTreatmentViewModel.status.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                is TreatmentCreated -> {
                    Toast.makeText(this.context, "Treatment created", Toast.LENGTH_SHORT).show()
                    this.findNavController()
                        .navigate(R.id.action_initTreatmentFragment_to_homeFragment)
                }
                is Error -> Toast.makeText(
                    this.context,
                    "Error: ${status.message}",
                    Toast.LENGTH_SHORT
                ).show()
                is Loading -> {
                }
                is Empty -> {
                }
            }

        })
    }

}
