package com.yasir.sculpture.arena.di

import com.yasir.sculpture.arena.view.fragment.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel {
        HomeFragmentViewModel( get())
    }

}