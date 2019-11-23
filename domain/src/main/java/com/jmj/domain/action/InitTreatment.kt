package com.jmj.domain.action

import com.jmj.domain.id.IdGenerator
import com.jmj.domain.patient.Patient
import com.jmj.domain.treatment.Treatment
import com.jmj.domain.treatment.Treatments

class InitTreatment(private val treatments: Treatments, private val idGenerator: IdGenerator) {

    operator fun invoke(request: InitTreatmentRequest): ActionResult<Treatment> =
        try {
            val newTreatment = request.toTreatment()
            treatments.save(newTreatment)
            Success(newTreatment)
        }catch (e: Throwable){
            Failure(error =  e.localizedMessage)
        }

    private fun InitTreatmentRequest.toTreatment() = Treatment(
        id = idGenerator.next(),
        patient = Patient(
            id = idGenerator.next(),
            name = patient
        ),
        defaultOfficeId = officeId,
        derivation = patientSourceId
    )
}


data class InitTreatmentRequest(
    val officeId: String,
    val patientSourceId: String,
    val patient: String
)