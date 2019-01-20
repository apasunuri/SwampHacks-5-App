package com.example.abhin.swamphacksapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class Camera extends AppCompatActivity {
    Button camera;
    Button financialButton;
    Button storeButton;
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
        storeButton = findViewById(R.id.storeAmount);

        camera.setOnClickListener(new btnPhotoClicker());
        financialButton.setOnClickListener(new btnfinancialClicker());
        storeButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(bitmap != null) {
                    TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

                    if(textRecognizer.isOperational()) {
                        Log.i("Text Recognition", "is Operational");

                        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                        SparseArray<TextBlock> items = textRecognizer.detect(frame);
                        StringBuilder stringBuilder = new StringBuilder();
                        if(items.size() == 0) {
                            orderAdded.setText("Error during Scanning. Please Try Again.");
                        }
                        for(int i = 0; i < items.size(); i++) {
                            TextBlock item = items.valueAt(i);
                            if(item.getValue().charAt(0) != '$')
                                orderAdded.setText("Error during Scanning. Please Try Again");
                            stringBuilder.append(item.getValue());
                            stringBuilder.append('\n');
                        }
                        //orderAdded.setText(stringBuilder.toString());
                        Log.i("Text ", stringBuilder.toString());
                    }
                    else {
                        Log.i("Text Recognition", "is not Operational");
                    }
                }
            }
        });

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
