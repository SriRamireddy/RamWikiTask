package com.ramireddy.ramwikitask;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.ramireddy.ramwikitask.adapter.WikiAdapter;
import com.ramireddy.ramwikitask.model.Page;
import com.ramireddy.ramwikitask.model.WikiModel;
import com.ramireddy.ramwikitask.roomdata.WikiViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WikiViewModel mWordViewModel;
    WikiAdapter wikiAdapter;
    List<Page> pages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        pages=new ArrayList<>();
       wikiAdapter=new WikiAdapter(this,pages);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(wikiAdapter);
        mWordViewModel = ViewModelProviders.of(this).get(WikiViewModel.class);

        mWordViewModel.getRowcount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer<1){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://en.wikipedia.org/w/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Apis apiData = retrofit.create(Apis.class);
                    Call<WikiModel> callData = apiData.getQuery();
                    callData.enqueue(new Callback<WikiModel>() {
                        @Override
                        public void onResponse(Call<WikiModel> call, Response<WikiModel> response) {

                            WikiModel wikiModel=response.body();
                            Toast.makeText(MainActivity.this, ""+wikiModel.getQuery().getPages().size(), Toast.LENGTH_SHORT).show();
                            for(int i=0;i<wikiModel.getQuery().getPages().size();i++){
                                mWordViewModel.insert(wikiModel.getQuery().getPages().get(i));
                            }
                        }
                        @Override
                        public void onFailure(Call<WikiModel> call, Throwable t) {
                            Log.d("responseFailed", ""+t.getMessage());
                        }
                    });
                }
            }
        });

        mWordViewModel.getAllWords().observe(this, new Observer<List<Page>>() {
            @Override
            public void onChanged(List<Page> pages) {
                wikiAdapter.setPages(pages);
            }
        });
    }



}
