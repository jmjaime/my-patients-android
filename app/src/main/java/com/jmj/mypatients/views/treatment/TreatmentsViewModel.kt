package com.jmj.mypatients.views.treatment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmj.domain.action.Failure
import com.jmj.domain.action.FindMyTreatments
import com.jmj.domain.action.Success
import com.jmj.domain.action.model.TreatmentAbbreviatedModel
import kotlinx.coroutines.launch

class TreatmentsViewModel(private val findMyTreatments: FindMyTreatments) :
    ViewModel() {

    val myTreatments: MutableLiveData<List<TreatmentAbbreviatedModel>> = MutableLiveData()
    val error: MutableLiveData<String> = MutableLiveData()

    init {
        viewModelScope.launch {
            when (val result = findMyTreatments()) {
                is Success<List<TreatmentAbbreviatedModel>> -> myTreatments.value = result.value
                is Failure -> error.value = result.error
            }
        }
    }

}