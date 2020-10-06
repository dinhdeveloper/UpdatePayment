package com.dinh.repository;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.canhdinh.lib.alert.AlertSuccess;
import com.dinh.api.RequestPayment;
import com.dinh.api.ServiceGenerator;
import com.dinh.model.BaseResponseModel;
import com.dinh.model.BookingResultModel;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePaymentRepository  {

    RequestPayment requestPayment = ServiceGenerator.createService(RequestPayment.class);
    public MutableLiveData<BaseResponseModel<BookingResultModel>> updateImage(String payment_image) {
        final MutableLiveData<BaseResponseModel<BookingResultModel>> data = new MutableLiveData<BaseResponseModel<BookingResultModel>>();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (!TextUtils.isEmpty(payment_image)) {
            File fileAvatar = new File(payment_image);
            if (fileAvatar.exists()) {
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), fileAvatar);
                builder.addFormDataPart("payment_image", fileAvatar.getName(), fileBody);
            }
        }
        builder.addFormDataPart("id_booking", "74");
        builder.addFormDataPart("type_manager", "update_result_payment");
        builder.addFormDataPart("detect", "booking_manager")
                .setType(MultipartBody.FORM);
        RequestBody requestBody = builder.build();

        requestPayment.updateImage(requestBody).enqueue(new Callback<BaseResponseModel<BookingResultModel>>() {
            @Override
            public void onResponse(Call<BaseResponseModel<BookingResultModel>> call, Response<BaseResponseModel<BookingResultModel>> response) {
                if (response.isSuccessful()){
                    data.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModel<BookingResultModel>> call, Throwable t) {
                Log.e("onFailure",t.getMessage());
            }
        });
        return data;
    }
}
