package com.ramireddy.ramwikitask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private TextView textData,url;
    private ImageView wikiImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        textData=findViewById(R.id.data);
        wikiImage=findViewById(R.id.details_page_image);
        url=findViewById(R.id.urldata);

        String imgLink=getIntent().getStringExtra("url");
        String data=getIntent().getStringExtra("data");
        String fullurl=getIntent().getStringExtra("fullurl");

        if (!imgLink.isEmpty()) {
            Picasso.get().load(imgLink).into(wikiImage);
        }
        textData.setText(data);
        url.setText(fullurl);

    }
}
