package com.tuti.api;

import com.google.gson.Gson;
import com.tuti.api.authentication.AuthenticationResponse;
import com.tuti.api.authentication.User;
import com.tuti.api.authentication.UserCredentials;
import com.tuti.model.Operations;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
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
            okHttpClient= new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).build();
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

    public AuthenticationResponse SignIn(UserCredentials credentials){
        return (AuthenticationResponse) request(Operations.SIGN_IN,credentials,AuthenticationResponse.class);
    }

    public String Signup(UserCredentials credentials){
        return (String) request(Operations.SIGN_UP,credentials,String.class);
    }


    public Object request(String operation, Object toBeSent,Type toBeReceived ){

        String url = serverURL + operation;
        OkHttpClient okHttpClient = getOkHttpInstance();
        Gson gson = getGsonInstance();

        RequestBody requestBody = RequestBody.create(new Gson().toJson(toBeSent),JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (toBeReceived != String.class) {
                return gson.fromJson(response.body().string(), toBeReceived);
            }else{
                return response.body().string();
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
