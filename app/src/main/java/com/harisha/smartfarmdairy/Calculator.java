package com.harisha.smartfarmdairy;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {

    EditText p,r,t;
    Button f;
    RadioGroup rg;
    RadioButton s,c;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        p=(EditText)findViewById(R.id.principle);
        t=(EditText)findViewById(R.id.time);
        r=(EditText)findViewById(R.id.rate);
        rg=(RadioGroup)findViewById(R.id.radiog);
        tv=(TextView)findViewById(R.id.result);
        f=(Button)findViewById(R.id.get);

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(p.getText().toString().isEmpty()||t.getText().toString().isEmpty()||r.getText().toString().isEmpty()){

                   tv.setText("Please Provide All details");
                }
                int selectedId=rg.getCheckedRadioButtonId();
                s=(RadioButton)findViewById(selectedId);
                try {
                    Double pr = Double.parseDouble(p.getText().toString());
                    Double tr = Double.parseDouble(t.getText().toString());
                    Double rr = Double.parseDouble(r.getText().toString());

                if(s.getText().toString().equals("Simple Interest")){
                    Double sp=((pr*tr*rr)/100);
                    tv.setText("Simple Interest : "+sp);

                }
                else{
                    double CI = pr *
                            (Math.pow((1 + rr / 100), tr));
                    tv.setText("compound Interest : "+Double.toString(CI));
                }
                }catch (NumberFormatException e){

                }
            }
        });
    }
}
