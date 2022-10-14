package com.example.examen.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.examen.MainActivity;
import com.example.examen.R;
import com.example.examen.interfaces.CRUDInterface;
import com.example.examen.model.Editorial;
import com.example.examen.utils.Conexion;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditActivity extends AppCompatActivity {
EditText name;
EditText marc;
EditText sto;
EditText prec;
Button edit;
Spinner spinner;
CRUDInterface crudInterface;
int clave=0;
    List<Editorial> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        int id= getIntent().getExtras().getInt("id");
        String nombre=getIntent().getExtras().getString("nombre");
        String marca=getIntent().getExtras().getString("marca");
        String stock=getIntent().getExtras().getString("stock");
        String precio=getIntent().getExtras().getString("precio");
        spinner=findViewById(R.id.spinneredit);
        name=findViewById(R.id.nameeditText);
        marc=findViewById(R.id.apellidoseditText);
        sto=findViewById(R.id.edadeditText);
        prec=findViewById(R.id.pesoeditText);
        name.setText(nombre);
        marc.setText(marca);
        sto.setText(stock);
        prec.setText(precio);

        edit=findViewById(R.id.btnedit);
        listspinner();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView) adapterView.getChildAt(0)).setTextSize(18);

                String idCat = adapterView.getSelectedItem().toString();
                clave = Integer.parseInt(idCat.split(" ")[0]);



                Toast.makeText(getApplicationContext(), String.valueOf(clave) , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit(id,name.getText().toString(),marc.getText().toString(),sto.getText().toString(),prec.getText().toString(),String.valueOf(clave));
            }
        });

    }
    private void listspinner(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Conexion.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        crudInterface=retrofit.create(CRUDInterface.class);
        Call<List<Editorial>> call=crudInterface.getAllSpinner();
        call.enqueue(new Callback<List<Editorial>>() {
            @Override
            public void onResponse(Call<List<Editorial>> call, Response<List<Editorial>> response) {
                posts = response.body();
                String[] s =new String[posts.size()];
                for(int i=0;i<posts.size();i++)
                {
                    s[i]= String.valueOf(posts.get(i).getId())+" "+posts.get(i).getNombre();
                    final ArrayAdapter a = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, s);
                    a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    spinner.setAdapter(a);
                }
            }

            @Override
            public void onFailure(Call<List<Editorial>> call, Throwable t) {

            }
        });
    }
    private void edit(int id,String uno, String dos, String tres, String cuatro, String cinco){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Conexion.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudInterface=retrofit.create(CRUDInterface.class);
        HashMap<String,String> map=new HashMap<>();
        map.put("nombre",uno);
        map.put("marca",dos);
        map.put("stock",tres);
        map.put("precio",cuatro);
        map.put("idcategoria",cinco);

        Call<Void> call=crudInterface.edit(id,map);
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
    private void callMain(){
        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}