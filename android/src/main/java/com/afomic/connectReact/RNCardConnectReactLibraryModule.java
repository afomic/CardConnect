
package com.afomic.connectReact;


import android.support.annotation.NonNull;

import com.cardconnect.consumersdk.CCConsumer;
import com.cardconnect.consumersdk.CCConsumerTokenCallback;
import com.cardconnect.consumersdk.domain.CCConsumerAccount;
import com.cardconnect.consumersdk.domain.CCConsumerCardInfo;
import com.cardconnect.consumersdk.domain.CCConsumerError;
import com.cardconnect.consumersdk.utils.CCConsumerCardUtils;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;


public class RNCardConnectReactLibraryModule extends ReactContextBaseJavaModule {

    public RNCardConnectReactLibraryModule(ReactApplicationContext reactContext) {
        super(reactContext);
        setupConsumerApi();
    }

    @Override
    public String getName() {
        return "RNCardConnectReactLibrary";
    }

    @ReactMethod
    public void getCardToken(ReadableMap options, final Promise promise) {
        String cardNumber = options.getString("cardNumber");
        String cvv = options.getString("cvv");
        String expiryDate = options.getString("expiryDate");

        try {
            validateCardNumber(cardNumber);
            validateCvv(cvv);
            validateExpiryDate(expiryDate);

            CCConsumerCardInfo mCCConsumerCardInfo = new CCConsumerCardInfo();
            mCCConsumerCardInfo.setCardNumber(cardNumber);
            mCCConsumerCardInfo.setExpirationDate(expiryDate);
            mCCConsumerCardInfo.setCvv(cvv);

            CCConsumer.getInstance().getApi().generateAccountForCard(mCCConsumerCardInfo, new CCConsumerTokenCallback() {
                @Override
                public void onCCConsumerTokenResponseError(@NonNull CCConsumerError ccConsumerError) {
                    promise.reject(new Exception(ccConsumerError.getResponseMessage()));
                }

                @Override
                public void onCCConsumerTokenResponse(@NonNull CCConsumerAccount ccConsumerAccount) {
                    promise.resolve(ccConsumerAccount.getToken());
                }
            });
        } catch (ValidateException e) {
            promise.reject(e);
        }
    }

    private void validateCardNumber(String cardNumber) throws ValidateException {
        if (!CCConsumerCardUtils.validateCardNumber(cardNumber)) {
            throw new ValidateException("Invalid CardNumber");
        }
    }

    private void validateCvv(String cvv) throws ValidateException {
        if (!CCConsumerCardUtils.validateCvvNumber(cvv)) {
            throw new ValidateException("Invalid CVV");
        }
    }

    private void validateExpiryDate(String expiryDate) throws ValidateException {
        try {
            String[] array = expiryDate.split("/");
            if (!CCConsumerCardUtils.validateExpirationDate(array[0], array[1])) {
                throw new ValidateException("Invalid ExpiryDate");
            }
        } catch (Exception e) {
            throw new ValidateException("Invalid ExpiryDate");
        }

    }

    private void setupConsumerApi() {
        CCConsumer.getInstance().getApi().setEndPoint("https://fts.cardconnect.com:6443/cardsecure/cs");
        CCConsumer.getInstance().getApi().setDebugEnabled(true);
    }
}