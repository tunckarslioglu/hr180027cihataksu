package com.hr180027.cihat_aksu_final.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hr180027.cihat_aksu_final.R;
import com.hr180027.cihat_aksu_final.adapter.CarAdapter;
import com.hr180027.cihat_aksu_final.model.Car;
import com.hr180027.cihat_aksu_final.model.GithubResponse;
import com.hr180027.cihat_aksu_final.network.IGetCarList;
import com.hr180027.cihat_aksu_final.network.RetrofitInstance;
import com.hr180027.cihat_aksu_final.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.Objects;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    IGetCarList getCarList;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    private CarAdapter adapter;
    RecyclerView recyclerView;
    OnItemClickListener onItemClickListener;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        recyclerView = findViewById(R.id.recyclerView);

        getCarService();

    }

    private void getCarService() {
        showProgress(true);


        getCarList = RetrofitInstance.getCarList();

        compositeDisposable.add(getCarList.getCars()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<GithubResponse>() {
                    @Override
                    public void onNext(GithubResponse githubResponse) {
                        showProgress(false);

                        initRecyclerView((ArrayList<Car>) githubResponse.getResponse());

                    }

                    @Override
                    public void onError(Throwable e) {
                        showProgress(false);

                        Log.d("serverError", Objects.requireNonNull(e.getMessage()));

                    }

                    @Override
                    public void onComplete() {
                    }
                }));


        // compositeDisposable.dispose();

    }

    private void initRecyclerView(ArrayList<Car> carDetail) {

        onItemClickListener = (position, carDetail1) -> {
            Intent i = new Intent(MainActivity.this, DetailActivity.class);
            i.putExtra("CarObject", carDetail1);
            MainActivity.this.startActivity(i);

        };

        adapter = new CarAdapter(MainActivity.this, carDetail, onItemClickListener);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        LinearLayoutManager linearLayoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());

        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


    }

    @Override
    public void onBackPressed() {
        createExitDialog();
    }

    private void createExitDialog() {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(R.string.exitAppTitle)
                .setMessage(R.string.exitAppContent)
                .setCancelable(false)
                .setNegativeButton(context.getString(android.R.string.ok), (dialog, which) -> {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finishAffinity();
                })
                .setPositiveButton(context.getString(android.R.string.cancel), (dialog, which) -> dialog.dismiss()).create();
        builder.show();
    }
    private void showProgress(Boolean loading){
        View progressLayout= findViewById(R.id.progressLayout);
        if(loading)
            progressLayout.setVisibility(View.VISIBLE);
        else
            progressLayout.setVisibility(View.GONE);


    }
}