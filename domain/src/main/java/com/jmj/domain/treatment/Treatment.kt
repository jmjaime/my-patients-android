package com.jmj.domain.treatment

import com.jmj.domain.money.Money
import com.jmj.domain.office.Office
import com.jmj.domain.patient.Patient
import java.time.LocalDate

data class Treatment(
    val id: String,
    val patient: Patient,
    val defaultOffice: Office,
    val derivation: Derivation,
    private val sessions: MutableList<Session> = mutableListOf()
) {
    fun addSession(date: LocalDate, office: Office, fee: Money) =
        Session(
            number = sessions.size + 1,
            date = date,
            office = office,
            fee = fee
        ).also {
            sessions.add(it)
        }

    fun sessions() = sessions.toList()
}