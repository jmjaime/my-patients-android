package com.jmj.inmemoryrepository.treatment

import androidx.lifecycle.MutableLiveData
import com.jmj.domain.treatment.Treatment
import com.jmj.domain.treatment.Treatments

class InMemoryTreatments(private val treatments: MutableMap<String, Treatment> = mutableMapOf()) :
    Treatments {
    override suspend fun find(treatmentId: String) = treatments[treatmentId]

    override suspend fun save(treatment: Treatment) {
        treatments[treatment.id] = treatment
    }

    override suspend fun findByPatientName(patientName: String) =
        treatments.values.firstOrNull { it.patient.name == patientName }

    override suspend fun findAll() = treatments.values.toList()

}