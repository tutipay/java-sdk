package com.tuti.api;

import com.google.gson.Gson;
import com.tuti.api.authentication.AuthenticationResponse;
import com.tuti.api.authentication.UserCredentials;
import com.tuti.model.Operations;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class TutiApiClient {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static volatile OkHttpClient okHttpClient = null;
    private String serverURL;

    private static volatile Gson gson = null;

    public TutiApiClient(boolean isDevelopment){
        serverURL = getServerURL(isDevelopment);
    }

    private static synchronized OkHttpClient getOkHttpInstance(){
        if (okHttpClient==null){
            okHttpClient= new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).build();
        }
        return okHttpClient;
    }

    private static synchronized Gson getGsonInstance(){
        if (gson==null){
            gson = new Gson();
        }
        return gson;
    }
    private String getServerURL(boolean development) {
        String developmentHost = "https://beta.app.2t.sd/consumer/";
        String productionHost = "https://beta.app.2t.sd/consumer/";
        return development ? developmentHost : productionHost;
    }

    public AuthenticationResponse SignIn(@NotNull UserCredentials credentials){
        String url = serverURL + Operations.SIGN_IN;
        OkHttpClient okHttpClient = getOkHttpInstance();
        Gson gson = getGsonInstance();

        RequestBody requestBody = RequestBody.create(new Gson().toJson(credentials),JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            return gson.fromJson(response.body().string(),AuthenticationResponse.class);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
