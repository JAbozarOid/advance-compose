package com.example.advancecompose.core.ui.di.annotation

import androidx.lifecycle.ViewModel
import javax.inject.Qualifier
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
@Qualifier
annotation class ForViewModel(val value: KClass<out ViewModel>)