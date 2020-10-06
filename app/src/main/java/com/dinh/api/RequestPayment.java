package com.dinh.api;

import com.dinh.helper.Consts;
import com.dinh.model.BaseResponseModel;
import com.dinh.model.BookingResultModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RequestPayment {
    @Headers(Consts.HEADES)
    @POST(Consts.REST_ENDPOINT)
    Call<BaseResponseModel<BookingResultModel>> updateImage(@Body RequestBody params);
}
