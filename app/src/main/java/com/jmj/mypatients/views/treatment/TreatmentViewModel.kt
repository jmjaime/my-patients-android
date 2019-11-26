package com.jmj.mypatients.views.treatment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmj.domain.treatment.Treatment
import com.jmj.domain.treatment.Treatments
import kotlinx.coroutines.launch

class TreatmentViewModel(private val treatments: Treatments, id: String) : ViewModel() {

    lateinit var treatment: LiveData<Treatment>

    init {
        viewModelScope.launch {
            this@TreatmentViewModel.treatment = treatments.find(id)
        }
    }
}
