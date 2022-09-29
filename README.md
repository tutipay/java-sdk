# Noebs Java Client        

[![](https://jitpack.io/v/sd.noebs/java-sdk.svg)](https://jitpack.io/#sd.noebs/java-sdk)

### Add it in your root build.gradle

  ```sh
  repositories {
			...
			maven { url 'https://jitpack.io' }
		}
  ```


  ### Add the dependency
  
   ```sh
dependencies {
	         implementation 'com.github.tutipay:java-sdk:-SNAPSHOT' # add this stage always use the bleeding edge version
	}
 ```

 ## How does the api work

 We are trying to simplify the API as much as we can, while the API is currently rapidly changing we are using it in production capacities.

### How to use noebs sdk

1. Create an instance of the client

`val tutiApiClient = TutiApiClient()`

2. Depending on which services you are using, some services need to be authenticated first, we do that this way:
`tutiApiClient.authToken = token`

3. You are good to go now, let's start by a simple request that is getting (no)ebs public key:
But before that **most** of function signatures are this way, only varies a few parameters

```kotlin
public void getPublicKey(EBSRequest ebsRequest, TutiApiClient.ResponseCallable<TutiResponse> onResponse, TutiApiClient.ErrorCallable<TutiResponse> onError)
```

Now, let's try to use that API:

```kotlin
client.getPublicKey(com.tuti.api.ebs.EBSRequest(), { response, _ ->
            run {
                if (response != null) {
                    Log.i("Public key response", response.ebsResponse.pubKeyValue)
                }
            }
        }, { error, exception, rawResponse ->
        run {
            // You can log exceptions here
            Log.i("NOEBS", exception.stackTrace)
        }  
        })

```

**NOTES**

Some logic needs to run within the main thread, e.g., if you want to show a toast, or navigate to a new fragment, for that we simply update the code as following:

```kotlin
client.getPublicKey(com.tuti.api.ebs.EBSRequest(), { response, _ ->
            run {
                if (response != null) {
                    Log.i("Public key response", response.ebsResponse.pubKeyValue)
                }
            }
        }, { error, exception, rawResponse ->
        run {
            Handler(Looper.getMainLooper()).post {
              Toast.makeText(
                        applicationContext,
                        "Key downloading failed. Code: " + error?.code,
                        Toast.LENGTH_SHORT
                    ).show()
            }
            Log.i("NOEBS", exception.stackTrace)
        }  
        })
```

Note how the log is running outside the main looper, while the toast runs within the main thread's.