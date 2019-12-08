package com.jmj.mypatients.views.treatment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmj.domain.action.*
import com.jmj.domain.action.model.OfficeModel
import com.jmj.domain.action.model.PatientSourceModel
import kotlinx.coroutines.launch

class InitTreatmentViewModel(
    private val initTreatment: InitTreatment,
    private val findOffices: FindOffices,
    private val findPatientSources: FindPatientSources
) : ViewModel() {

    val availableOffices: MutableLiveData<List<OfficeModel>> = MutableLiveData()
    val availablePatientSources: MutableLiveData<List<PatientSourceModel>> = MutableLiveData()
    var status: MutableLiveData<InitTreatmentState> = MutableLiveData(Loading)
    val selectedOffice: MutableLiveData<OfficeModel> = MutableLiveData()
    val selectedPatientSource: MutableLiveData<PatientSourceModel> = MutableLiveData()
    val patientName: MutableLiveData<String> = MutableLiveData()

    init {
        status
        viewModelScope.launch {
            when (val result = findOffices.invoke()) {
                is Success -> this@InitTreatmentViewModel.availableOffices.value = result.value
                is Failure -> this@InitTreatmentViewModel.status.value = Error(result.error)
            }
        }
        viewModelScope.launch {
            when (val result = findPatientSources.invoke()) {
                is Success -> this@InitTreatmentViewModel.availablePatientSources.value =
                    result.value
                is Failure -> this@InitTreatmentViewModel.status.value = Error(result.error)
            }
        }
    }

    fun init() {
        if (validate())
            createTreatment()
        else
            status.value = Error("Please fill all fields")
    }


    private fun createTreatment() = viewModelScope.launch {
        status.value = initTreatment(
            InitTreatmentRequest(
                selectedOffice.value!!.id,
                selectedPatientSource.value!!.id,
                patientName.value!!,
                selectedPatientSource.value!!.fee
            )
        ).let {
            when (it) {
                is Success -> TreatmentCreated
                is Failure -> Error(it.error)

            }
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
