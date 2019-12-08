package com.jmj.domain.action.model

import com.jmj.domain.patient.Patient

data class PatientModel(val name:String)

fun Patient.toModel() = PatientModel(this.name)