package uz.shoxrux.main.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.shoxrux.main.data.network.ReelsService
import uz.shoxrux.main.data.repository.HomeRepositoryImpl
import uz.shoxrux.main.data.repository.ReelsRepositoryImpl
import uz.shoxrux.main.domain.reposiotry.HomeRepository
import uz.shoxrux.main.domain.reposiotry.ReelsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @[Provides Singleton]
    fun provideHomeRepository(firestore: FirebaseFirestore): HomeRepository {
        return HomeRepositoryImpl(firestore)
    }

    @[Provides Singleton]
    fun provideReelsService(retrofit: Retrofit): ReelsService {
        return retrofit.create(ReelsService::class.java)
    }

    @[Provides Singleton]
    fun provideReelsRepository(service: ReelsService): ReelsRepository {
        return ReelsRepositoryImpl(service)
    }

}