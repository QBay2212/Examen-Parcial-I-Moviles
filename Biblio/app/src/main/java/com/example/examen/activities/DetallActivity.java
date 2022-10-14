package com.example.examen.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examen.MainActivity;
import com.example.examen.R;
import com.example.examen.interfaces.CRUDInterface;
import com.example.examen.model.Libro;
import com.example.examen.utils.Conexion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallActivity extends AppCompatActivity {
    TextView idText;
    TextView nameText;
    TextView marcaText;
    TextView sockText;
    TextView precioText;
    TextView categoriaText;
    CRUDInterface crudInterface;
    List<Libro> products;
    Button edit;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detall);
        idText=findViewById(R.id.idText);
        nameText=findViewById(R.id.idName);
        marcaText=findViewById(R.id.idApellidos);
        sockText=findViewById(R.id.idEdad);
        precioText=findViewById(R.id.idPeso);
        categoriaText=findViewById(R.id.idEscuela);
        int id= getIntent().getExtras().getInt("id");
        edit=findViewById(R.id.editButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callEdit();
            }
        });
        delete=findViewById(R.id.deleteButton);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    delete(id);

            }
        });
        getOne(id);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void delete(int id){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Conexion.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface=retrofit.create(CRUDInterface.class);
        Call<Void>  call=crudInterface.delete(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()) {
                    Toast toast=Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                callMain();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast toast=Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });


    }
    private void getOne(int id){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Conexion.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface=retrofit.create(CRUDInterface.class);
        Call<List<Libro>>  call=crudInterface.getOne(id);

        call.enqueue(new Callback<List<Libro>> () {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if(!response.isSuccessful()) {
                    Toast toast=Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                products= response.body();
                idText.setText(String.valueOf(products.get(0).getIdproductos()));
                nameText.setText(products.get(0).getNombre());
                marcaText.setText(products.get(0).getMarca());
                sockText.setText(String.valueOf(products.get(0).getStock()));
                precioText.setText(String.valueOf(products.get(0).getPrecio()));
                categoriaText.setText(String.valueOf(products.get(0).getCategoria()));
            }

            @Override
            public void onFailure(Call<List<Libro>>  call, Throwable t) {
                Toast toast=Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ", t.getMessage());
            }
        });
    }
    private void callEdit(){
        Intent  intent= new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra("id",products.get(0).getIdproductos());
        intent.putExtra("nombre",products.get(0).getNombre());
        intent.putExtra("marca",products.get(0).getMarca());
        intent.putExtra("stock",String.valueOf(products.get(0).getStock()));
        intent.putExtra("precio",String.valueOf(products.get(0).getPrecio()));
        startActivity(intent);
    }
    private void callMain(){
        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}