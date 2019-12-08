package com.jmj.domain.treatment

import com.jmj.domain.money.Money
import com.jmj.domain.source.PatientSource

data class Derivation(val patientSource: PatientSource, val currentFee: Money)