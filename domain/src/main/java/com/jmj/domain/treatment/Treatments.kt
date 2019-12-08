package com.jmj.domain.treatment

interface Treatments {

    suspend fun find(treatmentId: String): Treatment?

    suspend fun save(treatment: Treatment)

    suspend fun findByPatientName(patientName: String): Treatment?

    suspend fun findAll(): List<Treatment>

}