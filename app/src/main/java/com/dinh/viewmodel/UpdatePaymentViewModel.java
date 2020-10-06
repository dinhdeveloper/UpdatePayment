package com.dinh.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dinh.model.BaseResponseModel;
import com.dinh.model.BookingResultModel;
import com.dinh.repository.UpdatePaymentRepository;

public class UpdatePaymentViewModel extends ViewModel {
    private MutableLiveData<BaseResponseModel<BookingResultModel>> data = new MutableLiveData<>();
    private MutableLiveData<Boolean> checkUpdate = new MutableLiveData<>();
    private UpdatePaymentRepository repository = new UpdatePaymentRepository();
    private String payment_image;

    public void init() {
        if (this.data != null) {
            data = repository.updateImage(payment_image);
            return;
        }
        this.data = new MutableLiveData<>();
    }

    public MutableLiveData<BaseResponseModel<BookingResultModel>> getData() {
        return data;
    }

    public void updateImagePayment(String image) {
        payment_image = image;
        repository.updateImage(image);
    }
}
