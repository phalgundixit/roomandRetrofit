package com.example.roomandretrofit.retrofit;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {

    //create a new iterface class


    //@GET indicates the method of retrival of the json, "posts" is the extention of the base url used in mainactivity.
    @GET("posts")
    //retrives the list of pojo from the call method in main activity(basically a method to make request to the api).
    Call<List<pojo>> getPosts();



    //this url gives comments of id = 2 only
    @GET("posts/2/comments")
    Call<List<commentpojo>> getComments();


    /* if u want to get comments of differernt id then replace 2 with the key for id in {} like {"id"}
     and to get it into the placeholder pass it as an argurment,
     @Path puts the postid argument in the url in place of "id",
     the key in the url and in @Path("id") should be same.*/
    @GET("posts/{id}/comments")
    Call<List<commentpojo>> getCommentsid(@Path("id") int postId);


    /*   if u want to pass values to url in query for ex: url like this : https://jsonplaceholder.typicode.com/posts?userId=1
       and fetch only records with user id = 1, put @Query() and the corresponding key for which u have to search the record,
       and pass a argument (value of userId)
       retrofit will automatically add ? to the url and make it posts?userId = 3(whatever u pass)*/
    @GET("posts")
    Call<List<pojo>> getPostsquery(@Query("userId") int userId);

    /*for multiple query parameters
    retrofit will automatically add '&' sign between the parameters
    int is a primitive, so it cant be null, use Interger if u want to pass null vlaue incase u dont want to use these querys,
    as it will be difficult to create a method for each combination*/

    @GET("posts")
    Call<List<pojo>> getPostsquerymultilple(@Query("userId") Integer[] userId, @Query("_sort") String sort, @Query("_order") String Order);


    /* if u want to use key-value pair dynamically i.e. not hardcode the key as "userId" as above
     use @QueryMap to pass the key and the value as parameters in map */
    @GET("posts")
    Call<List<pojo>> getPostsquerymultilpleQuryMap(@QueryMap Map<String, String> parameters);

    //if u want to get value from differrnt url, pass the url to the query
    @GET
    Call<List<commentpojo>> getCommentsurl(@Url String url);


    //post method to send the data to the server,@Body is used to send the parameters of type pojo
    @POST("posts")
    Call<pojo> createPost(@Body pojo post);

    /*alternative way to send data to the server using post mehtod without using pojo
    @Field is used to send the key-value pair where key is hardcoded and value is sent as a argument
    @FromUrlEncoded means the data is encoded like the url, data is not sent over url ,it is sent over body
    for ex: if we are sending userId = 23,title = "new title" and body = "new text", @FromUrlEncoding will put
    the symbols such as & and fill blank spaces and other specail characters, it ll go like : userId=23&title=new%20title&body=new%20text.
     @Filed is similar to @query, wherein @query sends the parameters in url @Filed sends them in http body request */
    @FormUrlEncoded
    @POST("posts")
    Call<pojo> createPostwithoutpojo(@Field("userId") int userId, @Field("title") String title, @Field("body") String text);

    /*for multiple parameters use @FieldMap
    */
    @FormUrlEncoded
    @POST("posts")
    Call<pojo> createPostFieldMap(@FieldMap Map<String,String> fileds);





    @POST("UserLoginDet/")
    Call<hrms> hrms(@Body RequestBody jsonParams);


    /*@PUT and @PATCH are used to update data(exsisting resource) to the server, generally applied to single item(just like update query in sql)
    @PUT will replace the whole data(exsisting resouce) with the one we send in @Body request(it will replace the exsisitng object with the object we send)
    so always while using @PUT we have to send the full json object,if the object dosen not exsists it creates a new object like post method(depends on api)
    @PATCH will replce only the change the filed that we send(if we send tite = "hello") only title will be updated, everything will remain the same*/
    @PUT("posts/{id}")
    Call<pojo> putPost(@Path("id") int id,@Body pojo post);

    @PATCH("posts/{id}")
    Call<pojo> patchPost(@Path("id") int id,@Body pojo post);


    /*delete method is used to remove data from the server
    for @DELETE request empty body is returned  */
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);


}
