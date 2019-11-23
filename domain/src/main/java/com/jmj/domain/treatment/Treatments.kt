package com.jmj.domain.treatment

interface Treatments {

    fun find(treatmentId: String): Treatment?

    fun save(treatment: Treatment)

    fun findByPatientName(patientName: String): Treatment?

    fun findAll(): List<Treatment>

}