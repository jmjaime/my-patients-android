package com.jmj.domain.action

import com.jmj.domain.action.model.OfficeModel
import com.jmj.domain.action.model.toModel
import com.jmj.domain.office.Offices

class FindOffices(private val offices: Offices) {

    suspend operator fun invoke(): ActionResult<List<OfficeModel>> =
        try {
            Success(offices.findAll().map { it.toModel() }.toList())
        } catch (e: Throwable) {
            Failure(error = e.localizedMessage)
        }
}