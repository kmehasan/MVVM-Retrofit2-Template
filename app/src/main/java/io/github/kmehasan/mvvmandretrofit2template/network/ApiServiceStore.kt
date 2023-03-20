import io.github.kmehasan.mvvmandretrofit2template.response.charecter_model.CharecterResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceStore {
    @GET("character")
    suspend fun getCharacterList():Response<CharecterResponse>
}