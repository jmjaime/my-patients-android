package com.jmj.domain.action

import com.jmj.domain.action.model.PatientSourceModel
import com.jmj.domain.action.model.toModel
import com.jmj.domain.source.PatientSources

class FindPatientSources(private val patientSources: PatientSources) {

    suspend operator fun invoke(): ActionResult<List<PatientSourceModel>> =
        try {
            Success(patientSources.findAll().map { it.toModel() }.toList())
        } catch (e: Throwable) {
            Failure(error = e.localizedMessage)
        }
}