package com.example.imageviewgallery;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class img_extend extends AppCompatActivity
{
    ImageView pictView;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_3);

        pictView=(ImageView) findViewById(R.id.fullimageviewer);
        String word = getIntent().getExtras().getString("img");
        pictView.setImageURI(Uri.parse(word));
    }
}
