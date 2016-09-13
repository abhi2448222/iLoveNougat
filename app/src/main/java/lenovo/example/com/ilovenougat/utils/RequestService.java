package lenovo.example.com.ilovenougat.utils;

/**
 * Created by Lenovo on 9/11/2016.
 *
 */
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
public class RequestService {
    public static RequestFactory getService(String url){

        Gson gson = new Gson();
        RequestFactory requestFactory;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        requestFactory = retrofit.create(RequestFactory.class);

        return requestFactory;
    }
}
