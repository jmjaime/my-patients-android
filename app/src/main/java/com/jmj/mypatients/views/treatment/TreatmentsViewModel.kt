package com.jmj.mypatients.views.treatment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmj.domain.treatment.Treatment
import com.jmj.domain.treatment.Treatments
import kotlinx.coroutines.launch

class TreatmentsViewModel(private val treatments: Treatments) : ViewModel() {

    lateinit var myTreatments: LiveData<List<Treatment>>

    init {
        viewModelScope.launch {
            this@TreatmentsViewModel.myTreatments = treatments.findAll()
        }
    }

}