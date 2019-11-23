package com.jmj.mypatients.views.treatment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmj.domain.action.Failure
import com.jmj.domain.action.InitTreatment
import com.jmj.domain.action.InitTreatmentRequest
import com.jmj.domain.action.Success
import com.jmj.domain.office.Office
import com.jmj.domain.office.Offices
import com.jmj.domain.source.PatientSource
import com.jmj.domain.source.PatientSources
import kotlinx.coroutines.launch

class InitTreatmentViewModel(
    private val initTreatment: InitTreatment,
    private val offices: Offices,
    private val patientSources: PatientSources
) : ViewModel() {

    lateinit var availableOffices: LiveData<List<Office>>
    lateinit var availablePatientSources: LiveData<List<PatientSource>>
    var status: MutableLiveData<InitTreatmentState> = MutableLiveData(Loading)
    val selectedOffice: MutableLiveData<Office> = MutableLiveData()
    val selectedPatientSource: MutableLiveData<PatientSource> = MutableLiveData()
    val patientName: MutableLiveData<String> = MutableLiveData()

    init {
        status
        viewModelScope.launch {
            this@InitTreatmentViewModel.availableOffices = offices.findAll()
        }
        viewModelScope.launch {
            this@InitTreatmentViewModel.availablePatientSources = patientSources.findAll()
        }
    }

    fun init() {
        status.value = if (validate())
            createTreatment()
        else
            Error("Please fill all fields")
    }


    private fun createTreatment() = initTreatment(
        InitTreatmentRequest(
            selectedOffice.value!!.id,
            selectedPatientSource.value!!.id,
            patientName.value!!
        )
    ).let {
        when (it) {
            is Success -> TreatmentCreated
            is Failure -> Error(it.error)

        }
    }

    private fun validate() =
        selectedOffice.value != null && selectedPatientSource.value != null && patientName.value != null

}

sealed class InitTreatmentState
object Loading : InitTreatmentState()
object Empty : InitTreatmentState()
data class Error(val message: String) : InitTreatmentState()
object TreatmentCreated : InitTreatmentState()
