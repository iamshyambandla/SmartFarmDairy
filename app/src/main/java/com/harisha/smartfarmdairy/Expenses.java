package com.harisha.smartfarmdairy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Expenses extends AppCompatActivity {
    private EditText seeds,fert,pest,eam,wages,others,note;
    private DatabaseHelper databaseHelper;
    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        seeds=findViewById(R.id.seeds);
        fert=findViewById(R.id.Fertilizers);
        databaseHelper=new DatabaseHelper(this);
        pest=findViewById(R.id.Pesticides);
        eam=findViewById(R.id.ecm);
        wages=findViewById(R.id.wages);
        others=findViewById(R.id.others);
        note=findViewById(R.id.note);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res=databaseHelper.insertdata(Integer.parseInt(seeds.getText().toString()),Integer.parseInt(fert.getText().toString()),Integer.parseInt(pest.getText().toString()),Integer.parseInt(eam.getText().toString()),Integer.parseInt(wages.getText().toString()),Integer.parseInt(others.getText().toString()),note.getText().toString());
                if (res){
                    Toast.makeText(Expenses.this, "Added", Toast.LENGTH_SHORT).show();
                    seeds.setText("");
                    fert.setText("");
                    pest.setText("");
                    eam.setText("");
                    wages.setText("");
                    others.setText("");
                    note.setText("");
                }else {
                    Toast.makeText(Expenses.this, "not Added due to error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
