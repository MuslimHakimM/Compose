package com.bangkit.composesubmission.di

import com.bangkit.composesubmission.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}