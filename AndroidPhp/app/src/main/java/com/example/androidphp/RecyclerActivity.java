package com.example.androidphp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerActivity extends AppCompatActivity {
private RecyclerView recyclerView;
List<Responsclass> responsclassList;
RecyclerView.LayoutManager layoutManager;
FetchDataAdapter fetchDataAdapter;
ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.fectchrecycelrview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Responsclass>> call  = apiInterface.getUsers();
        call.enqueue(new Callback<List<Responsclass>>() {
            @Override
            public void onResponse(Call<List<Responsclass>> call, Response<List<Responsclass>> response) {
                responsclassList = response.body();
                fetchDataAdapter = new FetchDataAdapter(getApplicationContext(),responsclassList);
                recyclerView.setAdapter(fetchDataAdapter);
            }

            @Override
            public void onFailure(Call<List<Responsclass>> call, Throwable t) {

            }
        });

    }
}
