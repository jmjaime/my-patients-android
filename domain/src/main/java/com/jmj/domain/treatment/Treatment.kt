package com.jmj.domain.treatment

import com.jmj.domain.patient.Patient

data class Treatment(
    val id: String,
    val patient: Patient,
    val defaultOfficeId: String,
    val derivation: Derivation
)