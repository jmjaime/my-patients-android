package com.jmj.domain.action

import com.jmj.domain.action.model.SessionModel
import com.jmj.domain.action.model.toModel
import com.jmj.domain.money.Money
import com.jmj.domain.office.Offices
import com.jmj.domain.treatment.Treatments
import java.time.LocalDate

class AddSession(private val treatments: Treatments, private val offices: Offices) {

    suspend operator fun invoke(request: NewSessionRequest): ActionResult<SessionModel> {
        val treatment = treatments.find(request.treatmentId) ?: return Failure("Invalid treatment")
        val office = offices.findById(request.officeId) ?: return Failure("Invalid office")
        return Success(treatment.addSession(request.date, office, request.fee).toModel())
    }

}

data class NewSessionRequest(
    val treatmentId: String,
    val date: LocalDate,
    val officeId: String,
    val fee: Money
)