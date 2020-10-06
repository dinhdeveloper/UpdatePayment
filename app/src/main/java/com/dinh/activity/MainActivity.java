package com.dinh.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.canhdinh.lib.alert.AlertError;
import com.canhdinh.lib.alert.AlertSuccess;
import com.canhdinh.lib.roundview.RoundTextView;
import com.canhdinh.lib.selectimage.BSImagePicker;
import com.dinh.viewmodel.UpdatePaymentViewModel;

import java.util.List;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity implements LifecycleOwner,BSImagePicker.OnSingleImageSelectedListener,
        BSImagePicker.OnMultiImageSelectedListener, BSImagePicker.ImageLoaderDelegate, BSImagePicker.OnSelectImageCancelledListener{

    private UpdatePaymentViewModel paymentViewModel;

    private RoundTextView btnUpdateResult, btnSubmit;
    private ImageView imvImageResult;

    private String payment_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnUpdateResult.setOnClickListener(v -> {
            BSImagePicker pickerDialog =new BSImagePicker.Builder("com.dinh.activity.fileprovider").build();
            pickerDialog.show(getSupportFragmentManager(), "picker");
        });

        btnSubmit.setOnClickListener(v -> {
            paymentViewModel = ViewModelProviders.of(MainActivity.this).get(UpdatePaymentViewModel.class);
            paymentViewModel.updateImagePayment(payment_image);
            paymentViewModel.getData().observe(this,bookingResultModelBaseResponseModel -> {
                if (bookingResultModelBaseResponseModel!=null){
                    AlertSuccess.showAlertSuccess(MainActivity.this, "Xác nhận", "Thành công");
                }else {
                    AlertError.showAlertError(MainActivity.this, "Lỗi cập nhật", "Thất bại");
                }
            });
        });
    }

    private void addControls() {
        btnUpdateResult = findViewById(R.id.btnUpdateResult);
        btnUpdateResult = findViewById(R.id.btnUpdateResult);
        btnSubmit = findViewById(R.id.btnSubmit);
        imvImageResult = findViewById(R.id.imvImageResult);
    }

    @Override
    public void loadImage(Uri imageUri, ImageView ivImage) {
        Glide.with(MainActivity.this).load(imageUri).into(ivImage);
    }

    @Override
    public void onMultiImageSelected(List<Uri> uriList, String tag) {

    }

    @Override
    public void onCancelled(boolean isMultiSelecting, String tag) {

    }

    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        Glide.with(MainActivity.this).load(uri).into(imvImageResult);
        payment_image = uri.getPath();
    }
}