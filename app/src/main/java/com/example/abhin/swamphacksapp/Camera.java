package com.example.abhin.swamphacksapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Camera extends AppCompatActivity {
    Button camera;
    Button financialButton;
    Button storeAmount;
    ImageView receiptImage;
    TextView orderAdded;
    Bitmap bitmap;
    private static final int CAM_REQUEST=1313;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        camera = findViewById(R.id.cameraButton);
        financialButton = findViewById(R.id.financialButton);
        receiptImage = findViewById(R.id.imageView);
        orderAdded = findViewById(R.id.textView);
        storeAmount = findViewById(R.id.storeAmount);

        camera.setOnClickListener(new btnPhotoClicker());
        financialButton.setOnClickListener(new btnfinancialClicker());

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST) {
            bitmap = (Bitmap) data.getExtras().get("data");
            receiptImage.setImageBitmap(bitmap);
        }
    }

    class btnPhotoClicker implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAM_REQUEST);
        }
    }

    class btnfinancialClicker implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(getApplicationContext(), financeviewer.class);
            startActivity(i);
        }
    }
}
