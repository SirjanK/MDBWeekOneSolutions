package com.example.skafle.weekonesolutions;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    private ImageView imgView; // We need this imageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imgView = (ImageView) findViewById(R.id.gal_imgview); // Assign the approp imgview

        Intent intent = getIntent(); // Get that intent
        /**
         *  Hefty line: We get the string extra from main activity by specifying the IMAGE_KEY key
         *  We then parse it into a Uri using the static method parse.
         *  Then assign this image Uri to our imageUri variable
         *  I did not naturally know this. Google and Stack Overflow are your friends!!
         */
        Uri imageUri = Uri.parse(intent.getStringExtra(MainActivity.IMAGE_KEY));
        imgView.setImageURI(imageUri); // Set that image. Note the specific method setImageUri
    }
}
