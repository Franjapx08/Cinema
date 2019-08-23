package service;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular")
    Call<Object> getPopularMovies(@Query("api_key") String api_key);

    @GET("movie/now_playing")
    Call<NowPlayingResponse> getNowPlaying(@Query("api_key") String api_key);
}
