package uz.shoxrux.main.data.network

import retrofit2.Response
import retrofit2.http.GET
import uz.shoxrux.main.data.dto.wiki.WikiListResponseDto

interface ReelsService {

    @GET("api.php?action=query&format=json&generator=random&grnlimit=5&grnnamespace=0&prop=pageimages|extracts&piprop=original&exintro&explaintext")
    suspend fun getRandomWiki() : Response<WikiListResponseDto>

}