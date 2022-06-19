package com.tuti.api;

import com.google.gson.Gson;
import com.tuti.api.authentication.SignInResponse;
import com.tuti.api.authentication.SignUpInfo;
import com.tuti.api.authentication.SignUpResponse;
import com.tuti.api.authentication.SignInInfo;
import com.tuti.model.Operations;
import okhttp3.*;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TutiApiClient {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static volatile OkHttpClient okHttpClient = null;
    private String serverURL;

    private static volatile Gson gson = null;



    private boolean isTest = false;


    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean test) {
        isTest = test;
    }

    public TutiApiClient(boolean isDevelopment){
        serverURL = getServerURL(isDevelopment);
    }

    private static synchronized OkHttpClient getOkHttpInstance(){
        if (okHttpClient==null){
            okHttpClient= new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();
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

    public void SignIn(SignInInfo credentials,ResponseCallable<SignInResponse> onResponse,ErrorCallable onError){
        request(serverURL + Operations.SIGN_IN,credentials, SignInResponse.class,onResponse,onError);
    }

    public void Signup(SignUpInfo signUpInfo,ResponseCallable<SignUpResponse> onResponse,ErrorCallable onError){
        request( serverURL + Operations.SIGN_UP,signUpInfo,SignUpResponse.class,onResponse,onError);
    }


    public void request(String URL, Object JSONToBeSent, Type ClassExpectedToBeReceived,  ResponseCallable onResponse,ErrorCallable onError ){

        // create a runnable to run it in a new thread (so main thread never hangs)
        Runnable runnable = () ->{
                Object finalResponse  = null;
                OkHttpClient okHttpClient = getOkHttpInstance();
                Gson gson = getGsonInstance();

                RequestBody requestBody = RequestBody.create(new Gson().toJson(JSONToBeSent),JSON);

                Request request = new Request.Builder()
                        .url(URL)
                        .post(requestBody)
                        .build();

                try (Response response = okHttpClient.newCall(request).execute()) {
                    // check what format is expected to be returned
                    if (ClassExpectedToBeReceived == String.class || ClassExpectedToBeReceived == null) {
                        finalResponse = response.body().string();
                    }else{
                        finalResponse =  gson.fromJson(response.body().string(), ClassExpectedToBeReceived);
                    }

                    // call onResponse if request has succeeded
                    if(onResponse != null) { onResponse.call(finalResponse); }

                }catch(Exception e){
                    // call onError if request has failed
                    if(onError != null){ onError.call(e); }
                }
        };

        // unit testing concurrent code on multiple threads is hard
        if (isTest == true){
            new Thread(runnable).run();
        }else {
            new Thread(runnable).start();
        }

    }

    public interface ResponseCallable<T> {
        public void call(T param);
    }

    public interface ErrorCallable {
        public void call(Exception param);
    }
}

