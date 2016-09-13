package lenovo.example.com.ilovenougat.utils;

/**
 * Created by Lenovo on 9/11/2016.
 */
import lenovo.example.com.ilovenougat.model.ZapposResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface RequestFactory {

    @GET("Search/")
    Call<ZapposResult> getSearchResults(@Query("term") String term,@Query("key") String key);

}
