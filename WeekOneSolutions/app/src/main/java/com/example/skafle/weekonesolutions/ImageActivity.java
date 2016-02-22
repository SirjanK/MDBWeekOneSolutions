package com.example.skafle.weekonesolutions;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imgView = (ImageView) findViewById(R.id.gal_imgview);

        Intent intent = getIntent();
        Uri imageUri = Uri.parse(intent.getStringExtra(MainActivity.IMAGE_KEY));
        imgView.setImageURI(imageUri);
    }
}
