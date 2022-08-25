package com.tuti.api;

import com.google.gson.Gson;
import com.tuti.api.authentication.SignInRequest;
import com.tuti.api.authentication.SignInResponse;
import com.tuti.api.authentication.SignUpRequest;
import com.tuti.api.authentication.SignUpResponse;
import com.tuti.api.data.Card;
import com.tuti.api.data.Cards;
import com.tuti.api.data.PaymentToken;
import com.tuti.api.data.RequestMethods;
import com.tuti.api.data.ResponseData;
import com.tuti.api.data.TutiResponse;
import com.tuti.api.ebs.EBSRequest;
import com.tuti.api.ebs.EBSResponse;
import com.tuti.model.Operations;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class TutiApiClient {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private static volatile OkHttpClient okHttpClient = null;
    private static volatile Gson gson = null;
    private String serverURL;
    private boolean isSingleThreaded = false;
    private String authToken = null;

    public String getAuthToken() {
        return authToken;
    }

    public String getServerURL() {
        return serverURL;
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

    @Deprecated
    public TutiApiClient(boolean isDevelopment) {
        serverURL = getServerURL(isDevelopment);
    }

    public TutiApiClient(String token) {
        this.authToken = token;
    }

    private static HttpLoggingInterceptor getLogger() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    public TutiApiClient() {
        serverURL = getServerURL(false);
    }

    private static synchronized OkHttpClient getOkHttpInstance() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder().addInterceptor(getLogger()).connectTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();
        }
        return okHttpClient;
    }

    private static synchronized Gson getGsonInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    private String getServerURL(boolean development) {
        String developmentHost = "staging.app.2t.sd/consumer/";
        String productionHost = "staging.app.2t.sd/consumer/";
        return development ? developmentHost : productionHost;
    }

    public void SignIn(SignInRequest credentials, ResponseCallable<SignInResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.SIGN_IN, credentials, SignInResponse.class, TutiResponse.class, onResponse, onError, null);
    }

    public void ChangePassword(SignInRequest credentials, ResponseCallable<SignInResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.ChangePassword, credentials, SignInResponse.class, TutiResponse.class, onResponse, onError, null);
    }

    /**
     * OneTimeSignIn allows tutipay users to sign in via a code we send to their phone numbers
     * Notice: this method ONLY works for tutipay registered devices, at the moment
     * it doesn't support a sign in from a new device, as it relies on the user
     * signing a message via their private key
     * @param credentials
     * @param onResponse
     * @param onError
     */
    public void OneTimeSignIn(SignInRequest credentials, ResponseCallable<SignInResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.SINGLE_SIGN_IN, credentials, SignInResponse.class, TutiResponse.class, onResponse, onError, null);
    }

    /**
     * GenerateOtpSignIn service used to request an otp to be sent to the user's registered sms phone number
     *
     * @param credentials
     * @param onResponse
     * @param onError
     */
    public void GenerateOtpSignIn(SignInRequest credentials, ResponseCallable<SignInResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.GENERATE_LOGIN_OTP, credentials, SignInResponse.class, TutiResponse.class, onResponse, onError, null);
    }

    /**
     * RefreshToken used to refresh an existing token to keep user's session valid.
     * @param credentials
     * @param onResponse a method that is used to handle successful cases
     * @param onError a method to handle on error cases
     */
    public void RefreshToken(SignInRequest credentials, ResponseCallable<SignInResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.REFRESH_TOKEN, credentials, SignInResponse.class, TutiResponse.class, onResponse, onError, null);
    }


    /**
     *
     * @param signUpRequest
     * @param onResponse
     * @param onError
     */
    public void Signup(SignUpRequest signUpRequest, ResponseCallable<SignUpResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.SIGN_UP, signUpRequest, SignUpResponse.class, TutiResponse.class, onResponse, onError, null);
    }

    /**
     * VerifyFirebase used to verify a verification ID token that was sent to a user. It sets is_activiated
     * flag as true for the selected user. This is basically an in-background operation, and as though it shouldn't
     * block the UI, nor does the implementation should care too much about the returned object.
     * @param signUpRequest
     * @param onResponse
     * @param onError
     */
    public void VerifyFirebase(SignUpRequest signUpRequest, ResponseCallable<SignUpResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.VERIFY_FIREBASE, signUpRequest, SignUpResponse.class, TutiResponse.class, onResponse, onError, null);
    }

    public void sendEBSRequest(String URL, EBSRequest ebsRequest, ResponseCallable<EBSResponse> onResponse, ErrorCallable<EBSResponse> onError) {
        sendRequest(RequestMethods.POST, URL, ebsRequest, EBSResponse.class, EBSResponse.class, onResponse, onError, null);
    }

    public void getCards(ResponseCallable<Cards> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.GET, serverURL + Operations.GET_CARDS, null, Cards.class, TutiResponse.class, onResponse, onError, null);
    }

    public void getPublicKey(EBSRequest ebsRequest, ResponseCallable<TutiResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.PUBLIC_KEY, ebsRequest, TutiResponse.class, TutiResponse.class, onResponse, onError, null);
    }

    public void getIpinPublicKey(Object ebsRequest, ResponseCallable<TutiResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.IPIN_key, ebsRequest, TutiResponse.class, TutiResponse.class, onResponse, onError, null);
    }


    public void addCard(Object card, ResponseCallable<String> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.ADD_CARD, card, String.class, TutiResponse.class, onResponse, onError, null);
    }

    public void editCard(Card card, ResponseCallable<String> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.PUT, serverURL + Operations.EDIT_CARD, card, String.class, TutiResponse.class, onResponse, onError, null);
    }

    public void deleteCard(Card card, ResponseCallable<String> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.DELETE, serverURL + Operations.DELETE_CARD, card, String.class, TutiResponse.class, onResponse, onError, null);
    }

    public void billInquiry(Object request, ResponseCallable<TutiResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.BILL_INQUIRY, request, TutiResponse.class, TutiResponse.class, onResponse, onError, null);
    }

    public void generatePaymentToken(PaymentToken request, ResponseCallable<TutiResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.GeneratePaymentToken, request, TutiResponse.class, TutiResponse.class, onResponse, onError, null);
    }

    public void getPaymentToken(String uuid, ResponseCallable<TutiResponse> onResponse, ErrorCallable<TutiResponse> onError) {
        sendRequest(RequestMethods.GET, serverURL + Operations.GetPaymentToken, null, TutiResponse.class, TutiResponse.class, onResponse, onError, null, "uuid", uuid);
    }

    public void quickPayment(EBSRequest request, ResponseCallable<PaymentToken> onResponse, ErrorCallable<PaymentToken> onError) {
        sendRequest(RequestMethods.POST, serverURL + Operations.QuickPayment, request, PaymentToken.class, PaymentToken.class, onResponse, onError, null);
    }

    public Thread sendRequest(RequestMethods method, String URL, Object requestToBeSent, Type ResponseType, Type ErrorType, ResponseCallable onResponse, ErrorCallable onError, Map<String, String> headers, String ...params) {

        // create a runnable to run it in a new thread (so main thread never hangs)
        Runnable runnable = () -> {

            OkHttpClient okHttpClient = getOkHttpInstance();

            Gson gson = getGsonInstance();
            RequestBody requestBody = RequestBody.create(gson.toJson(requestToBeSent), JSON);

            HttpUrl url;
            if (params != null) {
                url = new HttpUrl.Builder().scheme("https").host(URL).addQueryParameter(params[0], params[1]).build();
            }else {
                url = new HttpUrl.Builder().scheme("https").host(URL).build();
            }

            Request.Builder requestBuilder = new Request.Builder().url(url);
            if (authToken != null) requestBuilder.header("Authorization", authToken);

            //add additional headers set by the user
            if (headers != null) {
                for (String key : headers.keySet()) {
                    requestBuilder.header(key, headers.get(key));
                }
            }

            //check for http method set by the user
            if (method == RequestMethods.POST) {
                requestBuilder.post(requestBody);
            }else if (method == RequestMethods.DELETE) {
                requestBuilder.delete(requestBody);
            } else if (method == RequestMethods.PUT) {
                requestBuilder.put(requestBody);
            }else {

                requestBuilder.get();
            }

            Request request = requestBuilder.build();

            try (Response rawResponse = okHttpClient.newCall(request).execute()) {
                // check for http errors
                int responseCode = rawResponse.code();
                String responseBody = rawResponse.body().string();

                ResponseData responseData = new ResponseData(responseCode, responseBody, rawResponse.headers());
                if (responseCode >= 400 && responseCode < 600) {
                    // call onError if request has failed
                    if (onError != null) {
                        onError.call(parseResponse(responseBody, rawResponse, ErrorType), null, responseData);
                    }
                } else {
                    // call onResponse if request has succeeded
                    if (onResponse != null) {
                        onResponse.call(parseResponse(responseBody, rawResponse, ResponseType), responseData);
                    }
                }

            } catch (Exception exception) {
                exception.printStackTrace();
                if (onError != null) onError.call(null, exception, null);
            }
        };

        // unit testing concurrent code on multiple threads is hard

        Thread thread = new Thread(runnable);

        if (isSingleThreaded) {
            thread.run();
        } else {
            thread.start();
        }

        return thread;
    }

    private Object parseResponse(String responseAsString, Response rawResponse, Type ResponseType) throws IOException {
        Gson gson = getGsonInstance();
        return isTypeStringOrNull(ResponseType) ? responseAsString : gson.fromJson(responseAsString, ResponseType);
    }

    private boolean isTypeStringOrNull(Type type) {
        return (type == String.class || type == null);
    }

    public interface ResponseCallable<T> {
        void call(T objectReceived, ResponseData rawResponse);
    }

    public interface ErrorCallable<T> {
        void call(T errorReceived, Exception exception, ResponseData rawResponse);
    }
}

