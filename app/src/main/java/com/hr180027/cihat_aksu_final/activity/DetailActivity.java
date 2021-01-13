package com.hr180027.cihat_aksu_final.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hr180027.cihat_aksu_final.R;
import com.hr180027.cihat_aksu_final.model.Car;

public class DetailActivity extends AppCompatActivity {

    Context context;
    ImageView imageView;
    TextView titleTv,detailTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        context = this;
        imageView = findViewById(R.id.imageView);
        titleTv = findViewById(R.id.titleTv);
        detailTv = findViewById(R.id.detailTv);


        Car item = (Car) getIntent().getSerializableExtra("CarObject");


      //  titleTv.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
        titleTv.setText(item.getCarName());
        detailTv.setText(item.getDetail());


        setImageView(item.getLogoUrl());


    }

    private void setImageView(String imageUrl) {
        RequestOptions options = new RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(context).
                asBitmap()
                .load(imageUrl)
                .apply(options)
                .skipMemoryCache(true)
                .into(imageView);
    }
}