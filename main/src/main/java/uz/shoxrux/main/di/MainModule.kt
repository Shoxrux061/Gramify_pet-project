package uz.shoxrux.main.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.shoxrux.main.data.repository.HomeRepositoryImpl
import uz.shoxrux.main.domain.reposiotry.HomeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @[Provides Singleton]
    fun provideHomeRepository(firestore: FirebaseFirestore): HomeRepository {
        return HomeRepositoryImpl(firestore)
    }

}