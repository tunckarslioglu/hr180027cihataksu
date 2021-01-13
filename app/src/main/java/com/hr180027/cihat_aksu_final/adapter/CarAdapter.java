package com.hr180027.cihat_aksu_final.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import com.hr180027.cihat_aksu_final.R;
import com.hr180027.cihat_aksu_final.model.Car;
import com.hr180027.cihat_aksu_final.utils.OnItemClickListener;

import java.util.ArrayList;


public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {


    public CarAdapter(Activity context, ArrayList<Car> carModel, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.carModel = carModel;
        this.onItemClickListener = onItemClickListener;
    }

    Activity context;
    ArrayList<Car> carModel;
    public OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.car_list_item, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Car item = carModel.get(position);


        holder.titleTextview.setText(item.getCarName());
        holder.detailTextview.setText(item.getDetail());
        holder.yearTv.setText(context.getString(R.string.yearText) + item.getYear());

        holder.itemView.setOnClickListener(v -> onItemClickListener.onClick(position, item));

        RequestOptions options = new RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE);


        Glide.with(context).
                asBitmap()
                .load(item.getLogoUrl())
                .apply(options)
                .skipMemoryCache(true)
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return carModel.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextview;
        TextView detailTextview;
        ImageView imageView;
        TextView yearTv;

        public ViewHolder(View view) {
            super(view);
            titleTextview = view.findViewById(R.id.titleTextview);
            detailTextview = view.findViewById(R.id.detailTv);
            yearTv = view.findViewById(R.id.yearTv);
            imageView = view.findViewById(R.id.carImageView);


        }
    }
}
