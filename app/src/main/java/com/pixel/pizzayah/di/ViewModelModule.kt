package com.pixel.pizzayah.di

import com.pixel.pizzayah.presentation.screen.PizzaViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::PizzaViewModel)
}