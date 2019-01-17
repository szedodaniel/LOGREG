package com.example.szedo.logreg;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private TextView Text_View_Benev;
    private EditText Edit_Text_nev;
    private Button Button_kijelentkezes;
    private AdatbazisSegito db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
        //adatLekeres();
        Button_kijelentkezes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(Main3Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();


            }
        });
    }

    public void init(){
        Text_View_Benev = (TextView) findViewById(R.id.Text_View_Benev);
        Button_kijelentkezes = (Button) findViewById(R.id.Button_kijelentkezes);
        Edit_Text_nev = (EditText) findViewById(R.id.Edit_Text_nev);
    }
    public void adatLekeres()
    {
        Cursor eredmeny = db.felhasznaloLekerdezes(Edit_Text_nev.getText().toString());
        //stringbuffer = hosszú string amihez hozzá fűzzük (appendeljük) a változókat
        StringBuffer stringBuffer = new StringBuffer();

        if (eredmeny!=null && eredmeny.getCount()>0)
        {
            while(eredmeny.moveToNext())
            {

                stringBuffer.append("Üdv:" + eredmeny.getString(1) + "\n");


            }
            Text_View_Benev.setText(stringBuffer.toString());
            Toast.makeText(this, "Adat sikeresen lekérve", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "Nincs adat, amit le lehet kérni", Toast.LENGTH_SHORT).show();
        }
    }
}
