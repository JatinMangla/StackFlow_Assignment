package com.example.assignment_try;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    QuestionAPIService apiService;
    RecyclerView recyclerView;
    QuestionAdapter adapter;
    List<Question> questions = new ArrayList<>();
    ProgressDialog progressDialog;

    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // apiService = RestClient.getClient().create(QuestionAPIService.class);
        recyclerView = findViewById(R.id.questionListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout= findViewById(R.id.Swipe);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        fetchQuetionList();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                questions.clear();
                fetchQuetionList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    private void fetchQuetionList() {
        Call<QuestionList> call = RestClient.getInstance().getInfo().fetchQuestions();
        call.enqueue(new Callback<QuestionList>() {
            @Override
            public void onResponse(Call<QuestionList> call, Response<QuestionList> response) {
                Log.d(TAG, "Total number of questions fetched : " + response.body().getQuestions().size());
                progressDialog.cancel();
                questions.addAll(response.body().getQuestions());
                adapter = new QuestionAdapter(questions ,getApplicationContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<QuestionList> call, Throwable t) {
                Log.e(TAG, "Got error : " + t.getLocalizedMessage());
                progressDialog.cancel();

                alert();
            }
        });

    }

    private void alert()
    {
        AlertDialog ald = new AlertDialog.Builder(this).setMessage("Internet Connection Problem").setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                        fetchQuetionList();
                        dialog.dismiss();
                progressDialog.show();
            }
        }).create();
        ald.show();



    }
}