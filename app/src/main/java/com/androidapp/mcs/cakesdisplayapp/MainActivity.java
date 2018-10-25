package com.androidapp.mcs.cakesdisplayapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidapp.mcs.cakesdisplayapp.adapter.MyAdapter;
import com.androidapp.mcs.cakesdisplayapp.model.Cakes;
import com.androidapp.mcs.cakesdisplayapp.service.MyWebService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    List<Cakes> cakesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.main_rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MyWebService myWebService = MyWebService.retrofit.create(MyWebService.class);
        Call<List<Cakes>> call = myWebService.getCakeItems();

        call.enqueue(new Callback<List<Cakes>>() {
            @Override
            public void onResponse(Call<List<Cakes>> call, Response<List<Cakes>> response) {
                cakesList = response.body();
                Toast.makeText(MainActivity.this, "Recieved" + cakesList.size() + "Cakes from Service", Toast.LENGTH_SHORT).show();
                displayData();
            }

            @Override
            public void onFailure(Call<List<Cakes>> call, Throwable t) {
                Log.d("onFailure: ", t.getMessage());
            }
        });

    }

    private void displayData() {
        if (cakesList != null) {
            MyAdapter adapter = new MyAdapter(cakesList, this);
            recyclerView.setAdapter(adapter);
        }

    }
}
