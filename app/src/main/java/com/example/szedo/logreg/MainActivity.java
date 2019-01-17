package com.example.szedo.logreg;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Edit_Text_nev, Edit_Text_jelszo;
    private Button Button_regisztralas, Button_bejelentkezes;
    private AdatbazisSegito db;

    public EditText getEdit_Text_nev() {
        return Edit_Text_nev;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        Button_regisztralas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regact = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(regact);
                finish();
            }
        });
        Button_bejelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bejelentkezes()){
                    Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });
    }
    public void init(){
        db = new AdatbazisSegito(this);
        Edit_Text_jelszo = (EditText) findViewById(R.id.Edit_Text_jelszo);
        Edit_Text_nev = (EditText) findViewById(R.id.Edit_Text_nev);
        Button_regisztralas = (Button) findViewById(R.id.Button_regisztralas);
        Button_bejelentkezes = (Button) findViewById(R.id.Button_bejelentkezes);

    }
    public boolean bejelentkezes()
    {
        String fnev = Edit_Text_nev.getText().toString();
        String jelszo = Edit_Text_jelszo.getText().toString();
        Cursor eredmeny = db.adatLekerdezes(fnev,jelszo);
        boolean mehet = false;

        if (eredmeny!=null && eredmeny.getCount()>0)
        {
            mehet = true;
            Toast.makeText(this, "Sikeres bejelentkezes", Toast.LENGTH_SHORT).show();
        }else
        {
            mehet = false;
            Toast.makeText(this, "Sikertelen bejelentkezes", Toast.LENGTH_SHORT).show();
        }
        return mehet;
    }



}
