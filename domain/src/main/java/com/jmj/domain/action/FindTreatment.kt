package com.jmj.domain.action

import com.jmj.domain.action.model.TreatmentModel
import com.jmj.domain.action.model.toModel
import com.jmj.domain.treatment.Treatments

class FindTreatment(private val treatments: Treatments) {

    suspend operator fun invoke(treatmentId: String): ActionResult<TreatmentModel> =
        try {
            treatments.find(treatmentId)?.let { Success(it.toModel()) }
                ?: Failure("Treatment not found")
        } catch (e: Throwable) {
            Failure(error = e.localizedMessage)
        }
}