/**
 * Created by Sirjan Kafle on 2/22/2016
 */

package com.example.skafle.weekonesolutions;

/* All my imports */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity { // Again double check AppCompatActivity is extended
    public static final String IMAGE_KEY = "image_key"; // Needed for my intent later on
    // Request codes to distinguish between a photo or email
    private static final int SELECT_PHOTO = 100;
    private static final int SELECT_EMAIL = 200;

    // Instance variables, in this case two buttons
    private Button pictureButton;
    private Button emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Connection to layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Finding our buttons from the layout. Remember the cast
        pictureButton = (Button) findViewById(R.id.pic_btn);
        emailButton = (Button) findViewById(R.id.email_btn);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        // Setting the Click Listeners
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigating to the LargeTextActivity using an intent.
                // Remember getApplicationContext() to provide that context parameter
                Intent intent = new Intent(getApplicationContext(), LargeTextActivity.class);
                startActivity(intent);
            }
        });

        pictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // It's a good idea to modularize your code
                pickPhoto();
            }
        });

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Again delgate tasks to private helper methods so your code is readable
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
        // This is where we come back to the MainActivity and must process some requestCode
        if (resultCode == RESULT_OK) { // We need this if statement. This is how we know a request actually happened.
            switch (requestCode) { // You can use if statements, but with multiple requestCodes, this is better
                case SELECT_PHOTO: // This is why we had this constant defined earlier.
                    Uri selectedImage = data.getData(); // Get the selected image as a Uri. You can look up what that is.
                    sendImage(selectedImage); // Delegate tasks
                    break; // Must need if you have multiple request codes in a switch statement.
            }
        }
    }

    // Here we direct to our gallery and pick a photo
    private void pickPhoto() {
        Intent photoIntent = new Intent(Intent.ACTION_GET_CONTENT); // This intent will have a specific action to get a file
        photoIntent.setType("image/*"); // Now a specific image file
        if (photoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(photoIntent, SELECT_PHOTO); // Here we will start the Intent.
            // The SELECT_PHOTO is important for onActivityResult above (requestCode)
        }
    }

    private void sendEmail(String address) {
        String[] addressArray = {address}; // We need an array as a parameter for putExtra below
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO); // Another action specified for our intent
        emailIntent.setData(Uri.parse("mailto:")); // This specifies only email
        emailIntent.putExtra(Intent.EXTRA_EMAIL, addressArray); // Put the email address as an extra
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(emailIntent, SELECT_EMAIL); // Light it up
        }
    }

    private void sendImage(Uri image) {
        Intent imageIntent = new Intent(getApplicationContext(), ImageActivity.class); // Send our image to ImageActivity
        imageIntent.putExtra(IMAGE_KEY, image.toString()); // Put the image Uri as a String since we can't pass Uri Objects
        startActivity(imageIntent); // Fire
    }
}
