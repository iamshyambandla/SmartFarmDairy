package com.harisha.smartfarmdairy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Income extends AppCompatActivity {
    private EditText income;
    private Button insub;
    private DatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        income=findViewById(R.id.income);
        insub=findViewById(R.id.insub);
        helper=new DatabaseHelper(this);
        insub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean res= helper.insertincome(Integer.parseInt(income.getText().toString()));
                if (res){
                    Toast.makeText(Income.this, "added successfully", Toast.LENGTH_SHORT).show();
                    income.setText("");
                }else{
                    Toast.makeText(Income.this, "error adding data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
