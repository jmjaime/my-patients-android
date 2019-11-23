package com.jmj.inmemoryrepository.treatment

import com.jmj.domain.treatment.Treatment
import com.jmj.domain.treatment.Treatments

class InMemoryTreatments(private val treatments: MutableMap<String, Treatment> = mutableMapOf()) :
    Treatments {
    override fun find(treatmentId: String) = treatments[treatmentId]

    override fun save(treatment: Treatment) {
        treatments[treatment.id] = treatment
    }

    override fun findByPatientName(patientName: String) =
        treatments.values.firstOrNull { it.patient.name == patientName }

    override fun findAll() = treatments.values.toList()

}