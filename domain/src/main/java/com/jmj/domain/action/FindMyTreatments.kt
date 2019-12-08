package com.jmj.domain.action

import com.jmj.domain.action.model.TreatmentAbbreviatedModel
import com.jmj.domain.action.model.toAbbreviatedModel
import com.jmj.domain.treatment.Treatments

class FindMyTreatments(private val treatments: Treatments) {

    suspend operator fun invoke(): ActionResult<List<TreatmentAbbreviatedModel>> =
        try {
            Success(treatments.findAll().map { it.toAbbreviatedModel() }.toList())
        } catch (e: Throwable) {
            Failure(error = e.localizedMessage)
        }
}