package com.jmj.domain

import com.jmj.domain.action.InitTreatment
import com.jmj.domain.id.IdGenerator
import org.koin.dsl.module
import java.util.*

val domainModule = module {
    single {
        InitTreatment(
            treatments = get(),
            idGenerator = UUIDIdGenerator()
        )
    }
}

class UUIDIdGenerator : IdGenerator {
    override fun next() = UUID.randomUUID().toString()
}