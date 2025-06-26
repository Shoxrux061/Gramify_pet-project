package uz.shoxrux.auth.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.shoxrux.auth.data.repository.AuthRepositoryImpl
import uz.shoxrux.auth.domain.repository.AuthRepository
import uz.shoxrux.auth.domain.use_case.AuthUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @[Provides Singleton]
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth, firestore)
    }

    @[Provides Singleton]
    fun provideAuthUseCase(
        repository: AuthRepository
    ): AuthUseCase {
        return AuthUseCase(repository)
    }

}