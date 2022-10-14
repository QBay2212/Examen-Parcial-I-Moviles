package com.example.apirest2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apirest2.activities.CreateActivity;
import com.example.apirest2.adapter.BiblioAdapters;
import com.example.apirest2.interfaces.CRUDInterface;
import com.example.apirest2.model.Libro;
import com.example.apirest2.utils.Conexion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Libro> products;
    CRUDInterface crudInterface;
    ListView listview;
    FloatingActionButton as;
    EditText search;
    ImageButton image,image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.listView);
        as=findViewById(R.id.createButton);
        search=findViewById(R.id.EditTextBusqueda);
        image=findViewById(R.id.imageButton2);
        image2=findViewById(R.id.imageButton3);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAll();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSearch(search.getText().toString());


            }
        });
        as.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callCreate();
            }
        });

        getAll();
    }
    private void callCreate(){
        Intent  intent= new Intent(getApplicationContext(), CreateActivity.class);
        startActivity(intent);
    }
    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);
        Call<List<Libro>> call = crudInterface.getAll();
        call.enqueue(new Callback<List<Libro>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }

                products = response.body();
                BiblioAdapters biblioAdapters=new BiblioAdapters(products,getApplicationContext());
                listview.setAdapter(biblioAdapters);
                products.forEach(p -> Log.i("Libreria: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
    private void getSearch(String nombre) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Conexion.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface = retrofit.create(CRUDInterface.class);
        Call<List<Libro>> call = crudInterface.getSearch(nombre);
        call.enqueue(new Callback<List<Libro>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if(!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }

                products = response.body();
                BiblioAdapters biblioAdapters=new BiblioAdapters(products,getApplicationContext());
                listview.setAdapter(biblioAdapters);
                products.forEach(p -> Log.i("Libros: ", p.toString()));
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
}