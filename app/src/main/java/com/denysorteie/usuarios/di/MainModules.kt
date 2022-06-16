package com.denysorteie.usuarios.di

import com.denysorteie.usuarios.data.UserRepository
import com.denysorteie.usuarios.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainModule = module {
    factory { UserRepository(get()) }
    viewModel { UserViewModel(get()) }
}