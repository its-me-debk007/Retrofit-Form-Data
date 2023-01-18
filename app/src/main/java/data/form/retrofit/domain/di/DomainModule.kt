package data.form.retrofit.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.form.retrofit.domain.repository.Repository
import data.form.retrofit.domain.use_case.UploadImageUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideUseCase(repository: Repository): UploadImageUseCase = UploadImageUseCase(repository)
}
