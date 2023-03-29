package com.recaptchaenterprise;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.recaptcha.Recaptcha;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaTasksClient;

import java.util.concurrent.Executor;

@ReactModule(name = RecaptchaEnterpriseModule.NAME)
public class RecaptchaEnterpriseModule extends ReactContextBaseJavaModule {
  public static final String NAME = "RecaptchaEnterprise";
  @Nullable
  private RecaptchaTasksClient recaptchaTasksClient = null;
  public RecaptchaEnterpriseModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  public void initRecaptchaEnterpriseClient(String siteKey, Promise promise) {
    Recaptcha
      .getTasksClient(getCurrentActivity().getApplication(), siteKey)
      .addOnSuccessListener(
        getCurrentActivity(),
        new OnSuccessListener<RecaptchaTasksClient>() {
          @Override
          public void onSuccess(RecaptchaTasksClient client) {
            recaptchaTasksClient = client;
            promise.resolve("RecaptchaClient creation success");
          }
        })
      .addOnFailureListener(
        getCurrentActivity(),
        new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
            promise.reject("error",e.getMessage());
          }
        });
  }
  @ReactMethod
  public void execute(String action,Promise promise){
    RecaptchaAction actionName = RecaptchaAction.custom("others");
    if(action == "login"){
      actionName = RecaptchaAction.LOGIN;
    }else if(action == "signup"){
      actionName = RecaptchaAction.SIGNUP;
    }
    assert recaptchaTasksClient != null;
    recaptchaTasksClient
      .executeTask(actionName)
      .addOnSuccessListener(
        getCurrentActivity(),
        new OnSuccessListener<String>() {
          @Override
          public void onSuccess(String token) {
            promise.resolve(token);
            // Handle success ...
            // See "What's next" section for instructions
            // about handling tokens.
          }
        })
      .addOnFailureListener(
        getCurrentActivity(),
        new OnFailureListener() {
          @Override
          public void onFailure(@NonNull Exception e) {
            promise.reject("error",e.getMessage());
            // Handle communication errors ...
            // See "Handle communication errors" section
          }
        });
  }


}
