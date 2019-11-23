package com.jmj.domain.action

sealed class ActionResult<T>

data class Success<T>(val value: T) : ActionResult<T>()
data class Failure<T>(val error: String) : ActionResult<T>()
