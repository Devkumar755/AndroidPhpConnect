package com.example.androidphp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.Script;
import android.text.Editable;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageUpload extends AppCompatActivity {

ImageView profileimage;
EditText title;
Button choosebtn,uploadbtn;
Uri imageuri;
int ImageRequest = 1;
Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);
        profileimage = findViewById(R.id.imageobject);
        title = findViewById(R.id.titleimage);
        choosebtn = findViewById(R.id.choosebtn);
        uploadbtn = findViewById(R.id.uploadebtn);
        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();
            }


        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ImageRequest || resultCode == RESULT_OK || data != null)
        {
            imageuri = data.getData();
            profileimage.setVisibility(View.VISIBLE);
            profileimage.setImageURI(imageuri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                title.setVisibility(View.VISIBLE);
                uploadbtn.setEnabled(true);
                choosebtn.setEnabled(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadimage() {
        String Image = ImagetoString();
        String title_text = title.getText().toString();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ImageRespons> call = apiInterface.uploadimage(title_text,Image);
        call.enqueue(new Callback<ImageRespons>() {
            @Override
            public void onResponse(Call<ImageRespons> call, Response<ImageRespons> response) {
                ImageRespons imageRespons = response.body();
                Toast.makeText(ImageUpload.this, imageRespons.getResponse(), Toast.LENGTH_LONG).show();
                profileimage.setVisibility(View.GONE);
                title.setVisibility(View.GONE);
                choosebtn.setEnabled(true);
                uploadbtn.setEnabled(false);
            }

            @Override
            public void onFailure(Call<ImageRespons> call, Throwable t) {
                Toast.makeText(ImageUpload.this,"something bad hapend in api",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,ImageRequest);
    }
    private String ImagetoString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imagebyt = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imagebyt,Base64.DEFAULT);
    }

}
