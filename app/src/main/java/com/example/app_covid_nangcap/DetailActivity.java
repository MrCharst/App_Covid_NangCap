package com.example.app_covid_nangcap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class DetailActivity extends AppCompatActivity {
    private  int positionCountry;

    PieChart pieChart1;
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);
        //Log.d("posi",String.valueOf(positionCountry));

        getSupportActionBar().setTitle("Details of "+SearchCountry.countryModelsList.get(positionCountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        AnhXa();
        GetData();

    }

    private void GetData() {

        tvCountry.setText(SearchCountry.countryModelsList.get(positionCountry).getCountry());
        tvCases.setText(SearchCountry.countryModelsList.get(positionCountry).getCases());
        tvRecovered.setText(SearchCountry.countryModelsList.get(positionCountry).getRecovered());
        tvCritical.setText(SearchCountry.countryModelsList.get(positionCountry).getCritical());
        tvActive.setText(SearchCountry.countryModelsList.get(positionCountry).getActive());
        tvTodayCases.setText(SearchCountry.countryModelsList.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(SearchCountry.countryModelsList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(SearchCountry.countryModelsList.get(positionCountry).getTodayDeaths());

        pieChart1.addPieSlice(new PieModel("Casess",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
        pieChart1.addPieSlice(new PieModel("Recoverds",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
        pieChart1.addPieSlice(new PieModel("Deathss",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
        pieChart1.addPieSlice(new PieModel("Actives",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));
        pieChart1.startAnimation();
    }


    private void AnhXa() {
        pieChart1 = findViewById(R.id.piechart1);
        tvCountry = findViewById(R.id.tvCountry);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}