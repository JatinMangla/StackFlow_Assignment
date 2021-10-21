package com.example.assignment_try;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuestionAPIService {

    @GET("/2.2/questions/no-answers?order=desc&sort=activity&site=stackoverflow")  //End Url
   // Call<QuestionList> fetchQuestions(@Query("tagged") String tags);

    Call<QuestionList> fetchQuestions();
}
