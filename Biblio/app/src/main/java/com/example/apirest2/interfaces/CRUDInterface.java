package com.example.apirest2.interfaces;

import com.example.apirest2.model.Editorial;
import com.example.apirest2.model.Libro;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CRUDInterface {
    @GET("libro")
    Call<List<Libro>> getAll();

    @GET("libro/search/{titulo}")
    Call<List<Libro>> getSearch(@Path("titulo") String titulo);


    @GET("editorial")
    Call<List<Editorial>> getAllSpinner();


    @GET("libro/{id}")
    Call<List<libro>> getOne(@Path("id") int id);


    @POST("libro")
    Call<Void> create(@Body HashMap<String,String> map);

    @DELETE("libro/{id}")
    Call<Void> delete(@Path("id") int id);

    @PUT("libro/{id}")
    Call<Void> edit( @Path("id") int id,@Body HashMap<String,String> map);
}
