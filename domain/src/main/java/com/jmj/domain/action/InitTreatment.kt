package com.jmj.domain.action

import com.jmj.domain.action.model.TreatmentModel
import com.jmj.domain.action.model.toModel
import com.jmj.domain.id.IdGenerator
import com.jmj.domain.money.Money
import com.jmj.domain.office.Office
import com.jmj.domain.office.Offices
import com.jmj.domain.patient.Patient
import com.jmj.domain.source.PatientSource
import com.jmj.domain.source.PatientSources
import com.jmj.domain.treatment.Derivation
import com.jmj.domain.treatment.Treatment
import com.jmj.domain.treatment.Treatments

class InitTreatment(
    private val treatments: Treatments,
    private val patientSources: PatientSources,
    private val offices: Offices,
    private val idGenerator: IdGenerator
) {

    suspend operator fun invoke(request: InitTreatmentRequest): ActionResult<TreatmentModel> {
        try {
            val office = offices.findById(request.officeId)
                ?: return Failure(error = "Office does not exist")
            val patientSource = patientSources.findById(request.patientSourceId) ?: return Failure(
                error = "Patient source does not exist"
            )

            val newTreatment = request.toTreatment(office, patientSource)
            treatments.save(newTreatment)
            return Success(newTreatment.toModel())
        } catch (e: Throwable) {
            return Failure(error = e.localizedMessage)
        }
    }

    private fun InitTreatmentRequest.toTreatment(office: Office, patientSource: PatientSource) =
        Treatment(
            id = idGenerator.next(),
            patient = Patient(
                id = idGenerator.next(),
                name = this.patient
            ),
            defaultOffice = office,
            derivation = Derivation(
                patientSource = patientSource,
                currentFee = this.fee
            )
        )


}


data class InitTreatmentRequest(
    val officeId: String,
    val patientSourceId: String,
    val patient: String,
    val fee: Money
)