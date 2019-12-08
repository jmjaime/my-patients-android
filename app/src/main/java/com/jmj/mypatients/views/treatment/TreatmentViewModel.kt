package com.jmj.mypatients.views.treatment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmj.domain.action.Failure
import com.jmj.domain.action.FindTreatment
import com.jmj.domain.action.Success
import com.jmj.domain.action.model.TreatmentModel
import kotlinx.coroutines.launch

class TreatmentViewModel(private val findTreatment: FindTreatment, id: String) : ViewModel() {

    val treatment: MutableLiveData<TreatmentModel> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()


    init {
        viewModelScope.launch {
            when (val result = findTreatment(id)) {
                is Success -> treatment.value = result.value
                is Failure -> error.value = result.error
            }
        }
    }
}
