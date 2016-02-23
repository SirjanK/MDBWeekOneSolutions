/**
 * Created by Sirjan Kafle on 2/22/2016
 */

package com.example.skafle.weekonesolutions;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    public static final String IMAGE_KEY = "image_key";
    private static final int SELECT_PHOTO = 100;
    private static final int SELECT_EMAIL = 200;

    private Button pictureButton;
    private Button emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pictureButton = (Button) findViewById(R.id.pic_btn);
        emailButton = (Button) findViewById(R.id.email_btn);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LargeTextActivity.class);
                startActivity(intent);
            }
        });

        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickPhoto();
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail("contact@mobiledevsberkeley.org");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) { // You can use if statements, but with multiple requestCodes, this is better
                case SELECT_PHOTO:
                    Uri selectedImage = data.getData();
                    sendImage(selectedImage);
                    break;
            }
        }
    }

    private void pickPhoto() {
        Intent photoIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoIntent.setType("image/*");
        if (photoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(photoIntent, SELECT_PHOTO);
        }
    }

    private void sendEmail(String address) {
        String[] addressArray = {address};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, addressArray);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(emailIntent, SELECT_EMAIL);
        }
    }

    private void sendImage(Uri image) {
        Intent imageIntent = new Intent(getApplicationContext(), ImageActivity.class);
        imageIntent.putExtra(IMAGE_KEY, image.toString());
        startActivity(imageIntent);
    }
}
