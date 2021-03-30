package com.example.roomandretrofit.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomandretrofit.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofit extends AppCompatActivity {

    TextView textView;
    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        textView = findViewById(R.id.txtview);

        //instatiate retrofit instance.

        //to forceably serilaize null, pass the gson variable to the GsonConverterFactory
        Gson gson = new GsonBuilder().serializeNulls().create();

        //logging the response
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //to add headers to all the methods in api class and also to log.
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {

                Request originalRequest = chain.request();
                Request newRequest = originalRequest.newBuilder().header("Content-Type:" ,"application/json;charset=UTF-8").build();
                return chain.proceed(newRequest);
            }
        }).addInterceptor(loggingInterceptor).build();
        /*the url contains list of joson objects
        .addConverterFactory converts json to gson */
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build();
        // Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.4.125:2020/SvcHRMS.svc/").addConverterFactory(GsonConverterFactory.create()).build();

        //create api instance,where u want to call the webserveice
        api = retrofit.create(Api.class);

        testApi();
        //getmethod with single json array containing json objects
        //GetPost();

        //getmethod with id = 2
        //getcomments();

        // get method to get comments from id = 3
        //getcommentsid(3);

        //getmethod to fetch values from single query parameter
        //GetPostquery(4);

        // get method to fetch multiple query values from url
        //GetPostquerymultiple();

        // get method to fetch multiple query values from url using hashmap
        //GetPostquerymultipleMap();

        //get method to get data from different url
        //getcommentsurl("https://jsonplaceholder.typicode.com/comments?postId=1");


        //post method to send data to the server with pojo
        //createPost();

        //post method to send data to the server with-out pojo
        //createPostwithoutPojo();

        //postMethod to send data to server with hash map
        //createPostFieldMap();


        //hrms();

        //put method that updates the server with the object sent
        //updatePut();

        //patch method that updates the server
        //updatePatch();

        //delete method to delete the json object in the server
        //deletePost();
    }

    private void testApi() {
        //create a call request method of list of type pojo and call the getposts method in api class
        Call<List<pojo>> call = api.getPosts();

        //call.enqueue creates a different thread for the callback method,as calling this in main thread will cause exception.
        call.enqueue(new Callback<List<pojo>>() {
            @Override
            public void onResponse(Call<List<pojo>> call, Response<List<pojo>> response) {
                //if respose is not sucessfull we get status code like 404,402 etc.
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                //receive the list of pojo
                List<pojo> list = response.body();
                for (pojo p : list) {
                    String Content = "";
                    Content += "ID: " + p.getId() + "\n";
                    Content += "User ID: " + p.getUserId() + "\n";
                    Content += "Title: " + p.getTitle() + "\n";
                    Content += "Text: " + p.getText() + "\n\n";

                    //append the content to the textview
                    textView.append(Content);
                }

            }

            @Override
            public void onFailure(Call<List<pojo>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void GetPost() {
        //create a call request method of list of type pojo and call the getposts method in api class
        Call<List<pojo>> call = api.getPosts();

        //call.enqueue creates a different thread for the callback method,as calling this in main thread will cause exception.
        call.enqueue(new Callback<List<pojo>>() {
            @Override
            public void onResponse(Call<List<pojo>> call, Response<List<pojo>> response) {
                //if respose is not sucessfull we get status code like 404,402 etc.
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                //receive the list of pojo
                List<pojo> list = response.body();
                for (pojo p : list) {
                    String Content = "";
                    Content += "ID: " + p.getId() + "\n";
                    Content += "User ID: " + p.getUserId() + "\n";
                    Content += "Title: " + p.getTitle() + "\n";
                    Content += "Text: " + p.getText() + "\n\n";

                    //append the content to the textview
                    textView.append(Content);

                }
            }

            @Override
            public void onFailure(Call<List<pojo>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void GetPostquery(int userId) {
        //create a call request method of list of type pojo and call the getposts method in api class
        Call<List<pojo>> call = api.getPostsquery(userId);

        //call.enqueue creates a different thread for the callback method,as calling this in main thread will cause exception.
        call.enqueue(new Callback<List<pojo>>() {
            @Override
            public void onResponse(Call<List<pojo>> call, Response<List<pojo>> response) {
                //if respose is not sucessfull we get status code like 404,402 etc.
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                //receive the list of pojo
                List<pojo> list = response.body();
                for (pojo p : list) {
                    String Content = "";
                    Content += "ID: " + p.getId() + "\n";
                    Content += "User ID: " + p.getUserId() + "\n";
                    Content += "Title: " + p.getTitle() + "\n";
                    Content += "Text: " + p.getText() + "\n\n";

                    //append the content to the textview
                    textView.append(Content);

                }
            }

            @Override
            public void onFailure(Call<List<pojo>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }


    private void GetPostquerymultiple() {
        //create a call request method of list of type pojo and call the getposts method in api class
        Call<List<pojo>> call = api.getPostsquerymultilple(new Integer[]{1, 4}, "id", "desc");

        //call.enqueue creates a different thread for the callback method,as calling this in main thread will cause exception.
        call.enqueue(new Callback<List<pojo>>() {
            @Override
            public void onResponse(Call<List<pojo>> call, Response<List<pojo>> response) {
                //if respose is not sucessfull we get status code like 404,402 etc.
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                //receive the list of pojo
                List<pojo> list = response.body();
                for (pojo p : list) {
                    String Content = "";
                    Content += "ID: " + p.getId() + "\n";
                    Content += "User ID: " + p.getUserId() + "\n";
                    Content += "Title: " + p.getTitle() + "\n";
                    Content += "Text: " + p.getText() + "\n\n";

                    //append the content to the textview
                    textView.append(Content);

                }
            }

            @Override
            public void onFailure(Call<List<pojo>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }


    private void GetPostquerymultipleMap() {
        //disadvantage of using map is it can take one key only once, we cant pass multipleparametrs for the same key
        Map<String, String> params = new HashMap<>();
        params.put("userId", "3");
        params.put("_sort", "id");
        params.put("_order", "desc");
        Call<List<pojo>> call = api.getPostsquerymultilpleQuryMap(params);

        //call.enqueue creates a different thread for the callback method,as calling this in main thread will cause exception.
        call.enqueue(new Callback<List<pojo>>() {
            @Override
            public void onResponse(Call<List<pojo>> call, Response<List<pojo>> response) {
                //if respose is not sucessfull we get status code like 404,402 etc.
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                //receive the list of pojo
                List<pojo> list = response.body();
                for (pojo p : list) {
                    String Content = "";
                    Content += "ID: " + p.getId() + "\n";
                    Content += "User ID: " + p.getUserId() + "\n";
                    Content += "Title: " + p.getTitle() + "\n";
                    Content += "Text: " + p.getText() + "\n\n";

                    //append the content to the textview
                    textView.append(Content);

                }
            }

            @Override
            public void onFailure(Call<List<pojo>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getcomments() {

        Call<List<commentpojo>> call = api.getComments();
        call.enqueue(new Callback<List<commentpojo>>() {
            @Override
            public void onResponse(Call<List<commentpojo>> call, Response<List<commentpojo>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                List<commentpojo> comments = response.body();

                for (commentpojo p : comments) {
                    String Content = "";
                    Content += "ID: " + p.getId() + "\n";
                    Content += "Post ID: " + p.getPostId() + "\n";
                    Content += "Name: " + p.getName() + "\n";
                    Content += "email: " + p.getEmail() + "\n";
                    Content += "Text: " + p.getText() + "\n\n";

                    //append the content to the textview
                    textView.append(Content);
                }
            }

            @Override
            public void onFailure(Call<List<commentpojo>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }


    private void getcommentsurl(String url) {

        Call<List<commentpojo>> call = api.getCommentsurl(url);
        call.enqueue(new Callback<List<commentpojo>>() {
            @Override
            public void onResponse(Call<List<commentpojo>> call, Response<List<commentpojo>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                List<commentpojo> comments = response.body();

                for (commentpojo p : comments) {
                    String Content = "";
                    Content += "ID: " + p.getId() + "\n";
                    Content += "Post ID: " + p.getPostId() + "\n";
                    Content += "Name: " + p.getName() + "\n";
                    Content += "email: " + p.getEmail() + "\n";
                    Content += "Text: " + p.getText() + "\n\n";

                    //append the content to the textview
                    textView.append(Content);
                }
            }

            @Override
            public void onFailure(Call<List<commentpojo>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void getcommentsid(int psotID) {

        Call<List<commentpojo>> call = api.getCommentsid(psotID);
        call.enqueue(new Callback<List<commentpojo>>() {
            @Override
            public void onResponse(Call<List<commentpojo>> call, Response<List<commentpojo>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                List<commentpojo> comments = response.body();

                for (commentpojo p : comments) {
                    String Content = "";
                    Content += "ID: " + p.getId() + "\n";
                    Content += "Post ID: " + p.getPostId() + "\n";
                    Content += "Name: " + p.getName() + "\n";
                    Content += "email: " + p.getEmail() + "\n";
                    Content += "Text: " + p.getText() + "\n\n";

                    //append the content to the textview
                    textView.append(Content);
                }
            }

            @Override
            public void onFailure(Call<List<commentpojo>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
        //assign values to contructor
        pojo p = new pojo(23, "new title", "new Text");

        //call the create post method in api class
        Call<pojo> call = api.createPost(p);

        call.enqueue(new Callback<pojo>() {
            @Override
            public void onResponse(Call<pojo> call, Response<pojo> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                pojo p = response.body();
                String Content = "";
                Content += "Code : " + response.code() + "\n";
                Content += "ID: " + p.getId() + "\n";
                Content += "User ID: " + p.getUserId() + "\n";
                Content += "Title: " + p.getTitle() + "\n";
                Content += "Text: " + p.getText() + "\n\n";

                //append the content to the textview
                textView.append(Content);
            }

            @Override
            public void onFailure(Call<pojo> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void createPostwithoutPojo() {


        //call the create post method in api class
        Call<pojo> call = api.createPostwithoutpojo(23, "new title1", "new text21");

        call.enqueue(new Callback<pojo>() {
            @Override
            public void onResponse(Call<pojo> call, Response<pojo> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                pojo p = response.body();
                String Content = "";
                Content += "Code : " + response.code() + "\n";
                Content += "ID: " + p.getId() + "\n";
                Content += "User ID: " + p.getUserId() + "\n";
                Content += "Title: " + p.getTitle() + "\n";
                Content += "Text: " + p.getText() + "\n\n";

                //append the content to the textview
                textView.append(Content);
            }

            @Override
            public void onFailure(Call<pojo> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void createPostFieldMap() {

        Map<String, String> params = new HashMap<>();
        params.put("userId", "3");
        params.put("title", "map title");
        //call the create post method in api class
        Call<pojo> call = api.createPostFieldMap(params);

        call.enqueue(new Callback<pojo>() {
            @Override
            public void onResponse(Call<pojo> call, Response<pojo> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                pojo p = response.body();
                String Content = "";
                Content += "Code : " + response.code() + "\n";
                Content += "ID: " + p.getId() + "\n";
                Content += "User ID: " + p.getUserId() + "\n";
                Content += "Title: " + p.getTitle() + "\n";
                Content += "Text: " + p.getText() + "\n\n";

                //append the content to the textview
                textView.append(Content);
            }

            @Override
            public void onFailure(Call<pojo> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void hrms() {





        Map<String, String> jsonParams = new LinkedHashMap<String, String>();
        jsonParams.put("UserName", "000001");
        jsonParams.put("Password", "Admin1234");
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), (new JSONObject(jsonParams)).toString());


        //assign values to contructor


        //call the create post method in api class
        Call<hrms> call = api.hrms(body);

        call.enqueue(new Callback<hrms>() {
            @Override
            public void onResponse(Call<hrms> call, Response<hrms> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());

                }
                hrms p = response.body();
                String aa = p.getEmpName();
                String Content = "";
                Content += "Code : " + response.code() + "\n";
                Content += "ID: " + p.getEmpName() + "\n";


                //append the content to the textview
                textView.append(Content);
            }

            @Override
            public void onFailure(Call<hrms> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void updatePut(){
        pojo p = new pojo(12,null,"new text");
        Call<pojo> call = api.putPost(5,p);
        call.enqueue(new Callback<pojo>() {
            @Override
            public void onResponse(Call<pojo> call, Response<pojo> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                pojo p = response.body();
                String Content = "";
                Content += "Code : " + response.code() + "\n";
                Content += "ID: " + p.getId() + "\n";
                Content += "User ID: " + p.getUserId() + "\n";
                Content += "Title: " + p.getTitle() + "\n";
                Content += "Text: " + p.getText() + "\n\n";

                //append the content to the textview
                textView.append(Content);
            }

            @Override
            public void onFailure(Call<pojo> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }



    private void updatePatch(){
        pojo p = new pojo(12,null,"new text");
        Call<pojo> call = api.patchPost(6,p);
        call.enqueue(new Callback<pojo>() {
            @Override
            public void onResponse(Call<pojo> call, Response<pojo> response) {
                if (!response.isSuccessful()) {
                    textView.setText("code: " + response.code());
                    return;
                }
                pojo p = response.body();
                String Content = "";
                Content += "Code : " + response.code() + "\n";
                Content += "ID: " + p.getId() + "\n";
                Content += "User ID: " + p.getUserId() + "\n";
                Content += "Title: " + p.getTitle() + "\n";
                Content += "Text: " + p.getText() + "\n\n";

                //append the content to the textview
                textView.append(Content);
            }

            @Override
            public void onFailure(Call<pojo> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }

    private void deletePost(){
        Call<Void> call = api.deletePost(5);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textView.setText("id: "+response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
