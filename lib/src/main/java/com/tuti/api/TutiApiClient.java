package com.tuti.api;

import com.google.gson.Gson;
import com.tuti.api.authentication.SignInResponse;
import com.tuti.api.authentication.SignUpInfo;
import com.tuti.api.authentication.SignUpResponse;
import com.tuti.api.authentication.SignInInfo;
import com.tuti.api.data.Card;
import com.tuti.api.data.Cards;
import com.tuti.api.ebs.EBSRequest;
import com.tuti.api.ebs.EBSResponse;
import com.tuti.model.Operations;
import okhttp3.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public class TutiApiClient {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static volatile OkHttpClient okHttpClient = null;
    private static volatile Gson gson = null;
    private String serverURL;
    private boolean isSingleThreaded = false;
    private String authToken = "";

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public boolean isSingleThreaded() {
        return isSingleThreaded;
    }

    public void setSingleThreaded(boolean singleThreaded) {
        isSingleThreaded = singleThreaded;
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

    public void SignIn(SignInInfo credentials,ResponseCallable<SignInResponse> onResponse,ErrorCallable<String> onError){
        sendPostRequest(serverURL + Operations.SIGN_IN,credentials, SignInResponse.class,String.class,onResponse,onError);
    }

    public void Signup(SignUpInfo signUpInfo,ResponseCallable<SignUpResponse> onResponse,ErrorCallable<String> onError){
        sendPostRequest( serverURL + Operations.SIGN_UP,signUpInfo,SignUpResponse.class,String.class,onResponse,onError);
    }

    public void sendEBSRequest(String URL,EBSRequest ebsRequest, ResponseCallable<EBSResponse> onResponse, ErrorCallable onError){
        sendPostRequest( URL,ebsRequest,EBSResponse.class,EBSResponse.class,onResponse,onError);
    }

    public void getCards( ResponseCallable<Cards> onResponse, ErrorCallable onError){
        sendGetRequest( serverURL + Operations.GET_CARDS, Cards.class,String.class,onResponse,onError);
    }

    public void addCard(Object card, ResponseCallable<String> onResponse, ErrorCallable onError){
        sendPostRequest( serverURL + Operations.ADD_CARD,card,String.class,String.class,onResponse,onError);
    }
    public void sendPostRequest(String URL, Object requestToBeSent, Type ResponseType, Type ErrorType, ResponseCallable onResponse, ErrorCallable onError ){

        // create a runnable to run it in a new thread (so main thread never hangs)
        Runnable runnable = () ->{

                OkHttpClient okHttpClient = getOkHttpInstance();
                Gson gson = getGsonInstance();

                RequestBody requestBody = RequestBody.create(gson.toJson(requestToBeSent),JSON);


                Request request = new Request.Builder()
                        .url(URL)
                        .header("Authorization",authToken)
                        .post(requestBody)
                        .build();

                try (Response response = okHttpClient.newCall(request).execute()) {
                    // check for http errors
                    if (response.code() >= 400 && response.code() < 600){
                        // call onError if request has failed
                        if(onError != null) { onError.call(processResponse(response,ErrorType),null,response.code()); }
                    }else{
                        // call onResponse if request has succeeded
                        if(onResponse != null) { onResponse.call(processResponse(response,ResponseType)); }
                    }

                }catch(Exception exception){
                    onError.call(null,exception,-1);
                }
        };

        // unit testing concurrent code on multiple threads is hard
        if (isSingleThreaded){
            new Thread(runnable).run();
        }else {
            new Thread(runnable).start();
        }
    }

    public void sendGetRequest(String URL, Type ResponseType,Type ErrorType, ResponseCallable onResponse, ErrorCallable onError ){

        // create a runnable to run it in a new thread (so main thread never hangs)
        Runnable runnable = () ->{
            OkHttpClient okHttpClient = getOkHttpInstance();
            Gson gson = getGsonInstance();

            Request request = new Request.Builder()
                    .url(URL)
                    .header("Authorization",authToken)
                    .get()
                    .build();

            try (Response response = okHttpClient.newCall(request).execute()) {
                // check for http errors
                if (response.code() >= 400 && response.code() < 600){
                    // call onError if request has failed
                    if(onError != null) { onError.call(processResponse(response,ErrorType),null,response.code()); }

                }else{
                    // call onResponse if request has succeeded
                    if(onResponse != null) { onResponse.call(processResponse(response,ResponseType)); }
                }

            }catch(Exception exception){
                exception.printStackTrace();
                // call on error in case of exception
                onError.call(null,exception,-1);
            }
        };

        // unit testing concurrent code on multiple threads is hard
        if (isSingleThreaded){
            new Thread(runnable).run();
        }else {
            new Thread(runnable).start();
        }
    }

    Object processResponse(Response response,Type ResponseType) throws IOException {
        Object finalResponse;
        if (ResponseType == String.class || ResponseType == null) {
            finalResponse = response.body().string();
        }else{
            finalResponse =  gson.fromJson(response.body().string(), ResponseType);
        }
        return finalResponse;
    }

    public interface ResponseCallable<T> {
         void call(T param);
    }

    public interface ErrorCallable<T> {
         void call(T param,Exception exception,int responseCode);
    }
}

