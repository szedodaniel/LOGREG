package com.example.szedo.logreg;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private EditText Edit_Text_regnev,Edit_Text_regjelszo,Edit_Text_regjelszoujra,Edit_Text_regtnev;
    private Button Button_regregisztralas;

    private AdatbazisSegito db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();

        Button_regregisztralas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(adatRogzites()){
                   Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                   startActivity(intent);
                   finish();
               }

            }
        });
    }
    public void init(){
        db = new AdatbazisSegito(this);
        Edit_Text_regnev = (EditText)findViewById(R.id.Edit_Text_regnev);
        Edit_Text_regjelszo = (EditText)findViewById(R.id.Edit_Text_regjelszo);
        Edit_Text_regjelszoujra = (EditText)findViewById(R.id.Edit_Text_regjelszoujra);
        Edit_Text_regtnev = (EditText)findViewById(R.id.Edit_Text_regtnev);
        Button_regregisztralas = (Button)findViewById(R.id.Button_regregisztralas);

    }
    public boolean adatRogzites()
    {
        boolean mehet = false;
        String fnev = Edit_Text_regnev.getText().toString();
        String jelszo = Edit_Text_regjelszo.getText().toString();
        String tnev = Edit_Text_regtnev.getText().toString();
        boolean eredmeny;
        if(!Edit_Text_regjelszo.getText().toString().equals(Edit_Text_regjelszoujra.getText().toString()))
        {
            eredmeny = false;

            Toast.makeText(this, "Nem egyeznek a jelszavak", Toast.LENGTH_SHORT).show();
        }
        else
        {

            eredmeny = db.adatRogzites(fnev,tnev,jelszo);
        }





        if (eredmeny)
        {
            mehet = true;
            Toast.makeText(this, "Sikeres adatrögzítés", Toast.LENGTH_SHORT).show();
        }else
        {
            mehet = false;
            Toast.makeText(this, "Sikertelen adatrögzítés", Toast.LENGTH_SHORT).show();
        }
        return mehet;
    }
}
