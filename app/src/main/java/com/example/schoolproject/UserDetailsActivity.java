package com.example.schoolproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class UserDetailsActivity extends AppCompatActivity {

    Button btCamera;
    ImageView ivPhoto;
    ActivityResultLauncher<Intent> arSmall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ActivityCompat.requestPermissions(this,

                new String[]{android.Manifest.permission.CAMERA,

                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,

                        android.Manifest.permission.READ_EXTERNAL_STORAGE},

                1);

        btCamera = findViewById(R.id.btCamera);
        ivPhoto = findViewById(R.id.ivPhoto);

        // Creating the ActivityResultLauncher
        arSmall = registerForActivityResult(

                new ActivityResultContracts.StartActivityForResult(),

                new ActivityResultCallback<ActivityResult>() {

                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        ivPhoto.setImageBitmap(bitmap);
                        ivPhoto.setImageBitmap(result.getData().getParcelableExtra("data"));
                    } });

        btCamera.setOnClickListener(new View.OnClickListener() {
            // Takes a photo using the phone's camera
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                arSmall.launch(intent);
            }});
    }
}