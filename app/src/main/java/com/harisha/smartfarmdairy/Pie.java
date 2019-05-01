package com.harisha.smartfarmdairy;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class Pie extends AppCompatActivity {
    private PieChart chart;
    private DatabaseHelper helper;
    private int[] yData = {5};
    StringBuilder builder;
    String text;
    private TextView textView;
    private String[] xData = {"Seeds", "Fertilizers", "Pesticides", "bills", "Wages", "Other"};
    int sedds, fert, pest, eam, wages, others, income, expenditure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        chart = findViewById(R.id.chart);
        textView = findViewById(R.id.profit);
        helper = new DatabaseHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteAll();
                chart.invalidate();
            }
        });
        builder=new StringBuilder();


        chart.setCenterText("Expenditure");
        chart.setHoleColor(Color.WHITE);
        chart.setRotationEnabled(true);
        chart.setDrawEntryLabels(false);
        chart.setDrawMarkerViews(false);
        chart.setHoleRadius(25);

        chart.setTransparentCircleAlpha(0);
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Toast.makeText(Pie.this, Float.toString(e.getY()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        Cursor res = helper.getexpenses();
        if (res.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "No entries", Toast.LENGTH_SHORT).show();
        } else {
            sedds = 0;
            fert = 0;
            pest = 0;
            eam = 0;
            wages = 0;
            others = 0;
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                sedds = sedds + res.getInt(0);
                fert = fert + res.getInt(1);
                pest = pest + res.getInt(2);
                eam = eam + res.getInt(3);
                wages = wages + res.getInt(4);
                others = others + res.getInt(5);
            }
            expenditure = sedds + fert + pest + eam + wages + others;
        }
        Cursor cursor = helper.getincome();
        if (cursor.getCount() == 0) {

        } else {
            income = 0;
            while (cursor.moveToNext()) {
                income = income + cursor.getInt(0);
            }
        }
        addDataSet();
        if (income != 0 && expenditure != 0) {
            int profit = income - expenditure;
            if (profit > 0) {
                String text = "Profit is " + String.valueOf(profit);
                Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            } else {
                profit = -profit;
                String text = "Loss is " + String.valueOf(profit);
                Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            }
        }

    getData();
        if (!builder.toString().isEmpty()) {
            String s = "Limit exceeded in " + builder.toString();
            AlertDialog.Builder dbuilder = new AlertDialog.Builder(this).setIcon(R.drawable.farmer).setTitle("Limit Exceeded").setMessage(s).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog=dbuilder.create();
            dialog.show();
        }
        if (text!=null){
            AlertDialog.Builder dbuilder = new AlertDialog.Builder(this).setIcon(R.drawable.farmer).setTitle("Notification").setMessage(text).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog=dbuilder.create();
            dialog.show();
        }
    }

    private void addDataSet() {

        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        yEntrys.add(new PieEntry(sedds, "seeds-" + String.valueOf(sedds)));
        yEntrys.add(new PieEntry(fert, "Fertilizers-" + String.valueOf(fert)));
        yEntrys.add(new PieEntry(pest, "Pesticides-" + String.valueOf(pest)));
        yEntrys.add(new PieEntry(eam, "Bills-" + String.valueOf(eam)));
        yEntrys.add(new PieEntry(wages, "wages-" + String.valueOf(wages)));
        yEntrys.add(new PieEntry(others, "Others-" + String.valueOf(others)));
        yEntrys.add(new PieEntry(income, "income-" + String.valueOf(income)));


        for (int i = 1; i < xData.length; i++) {
            xEntrys.add(xData[i]);
        }
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        colors.add(Color.GRAY);

        pieDataSet.setColors(colors);
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        chart.setData(pieData);
        chart.invalidate();


    }

    public void getData() {
        Cursor res = helper.getexpenses();
        if (res.getCount() == 0) {
        //    Toast.makeText(getApplicationContext(), "hii", Toast.LENGTH_SHORT).show();
        } else {
            sedds = 0;
            fert = 0;
            pest = 0;
            eam = 0;
            wages = 0;
            others = 0;
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                sedds = sedds + res.getInt(0);
                fert = fert + res.getInt(1);
                pest = pest + res.getInt(2);
                eam = eam + res.getInt(3);
                wages = wages + res.getInt(4);
                others = others + res.getInt(5);
            }
            expenditure = sedds + fert + pest + eam + wages + others;
            if (sedds>50000){
                builder.append("seeds ");
            }
            if (fert>50000){
                builder.append("Fertilizers ");
            }
            if (pest>50000){
                builder.append("Pesticides ");
            }
            if (eam>50000){
                builder.append("Bills ");
            }
            if (wages>50000){
                builder.append("Wages ");
            }
            if (others>50000){
                builder.append("seeds ");
            }
        }
        Cursor cursor = helper.getincome();
        if (cursor.getCount() == 0) {

        } else {
            income = 0;
            while (cursor.moveToNext()) {
                income = income + cursor.getInt(0);
            }
        }
        addDataSet();
        if (income != 0 && expenditure != 0) {
            int profit = income - expenditure;
            if (profit > 0) {
                 text = "Profit is " + String.valueOf(profit);
               // Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            } else {
                profit = -profit;
                 text = "Loss is " + String.valueOf(profit);
               // Toast.makeText(this, text, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }



}