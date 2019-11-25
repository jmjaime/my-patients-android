package com.jmj.domain.treatment

import androidx.lifecycle.LiveData

interface Treatments {

    suspend fun find(treatmentId: String):LiveData<Treatment>

    suspend fun save(treatment: Treatment)

    suspend fun findByPatientName(patientName: String):LiveData<Treatment>

    suspend fun findAll(): LiveData<List<Treatment>>

}