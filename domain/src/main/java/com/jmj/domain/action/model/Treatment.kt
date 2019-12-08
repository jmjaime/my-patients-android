package com.jmj.domain.action.model

import com.jmj.domain.money.Money
import com.jmj.domain.treatment.Derivation
import com.jmj.domain.treatment.Session
import com.jmj.domain.treatment.Treatment
import java.time.LocalDate

data class TreatmentModel(
    val id: String,
    val patient: PatientModel,
    val office: OfficeModel,
    val derivation: DerivationModel
)

fun Treatment.toModel() = TreatmentModel(
    id = this.id,
    patient = this.patient.toModel(),
    office = this.defaultOffice.toModel(),
    derivation = this.derivation.toModel()
)

data class TreatmentAbbreviatedModel(
    val id: String,
    val patient: PatientModel,
    val office: OfficeModel,
    val derivation: DerivationModel
)

fun Treatment.toAbbreviatedModel() = TreatmentAbbreviatedModel(
    id = this.id,
    patient = this.patient.toModel(),
    office = this.defaultOffice.toModel(),
    derivation = this.derivation.toModel()
)


data class DerivationModel(
    val patientSource: PatientSourceModel,
    val currentFee: Money
)

fun Derivation.toModel() = DerivationModel(
    patientSource = this.patientSource.toModel(),
    currentFee = this.currentFee
)

data class SessionModel(
    val number: Int,
    val date: LocalDate,
    val office: OfficeModel,
    val fee: Money
)

fun Session.toModel() = SessionModel(
    number = this.number,
    date = this.date,
    office = this.office.toModel(),
    fee = this.fee
)