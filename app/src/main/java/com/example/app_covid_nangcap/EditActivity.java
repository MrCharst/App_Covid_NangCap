package com.example.app_covid_nangcap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class EditActivity extends AppCompatActivity {

    EditText name,birthday,citizen,phone,address,departure,departureday,destination,destinationday,schedule,health;
    RadioButton genderNam,genderNu;
    Button btnEdit,btnCancel;
    String giotinh;
    public static Sqlite db;
    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        AnhXa();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null) {
            ID=bundle.getInt("Id");
            name.setText(bundle.getString("Name"));
            birthday.setText(bundle.getString("Birthday"));
            citizen.setText(bundle.getString("Citizen"));
            phone.setText(bundle.getString("Phone"));
            address.setText(bundle.getString("Address"));
            departure.setText(bundle.getString("Departure"));
            departureday.setText(bundle.getString("Departureday"));
            destination.setText(bundle.getString("Destination"));
            destinationday.setText(bundle.getString("Destinationday"));
            schedule.setText(bundle.getString("Schedule"));
            health.setText(bundle.getString("Health"));
            if (bundle.getString("Gender").equals("Nam")){
                genderNam.setChecked(true);
            }
            else {
                genderNu.setChecked(true);
            }

        }
        intent.putExtras(bundle);

        btnEdit.setOnClickListener((v)->
        {
            Intent i=new Intent();
            Bundle b=new Bundle();
            b.putInt("Id",ID);
            b.putString("Name",name.getText().toString());
            b.putString("Birthday",birthday.getText().toString());
            b.putString("Citizen",citizen.getText().toString());
            b.putString("Phone",phone.getText().toString());
            b.putString("Address",address.getText().toString());
            b.putString("Departure",departure.getText().toString());
            b.putString("Departureday",departureday.getText().toString());
            b.putString("Destination",destination.getText().toString());
            b.putString("Destinationday",destinationday.getText().toString());
            b.putString("Schedule",schedule.getText().toString());
            b.putString("Health",health.getText().toString());
            if (genderNam.isChecked()){
                giotinh="Nam";
            }
            if (genderNu.isChecked()){
                giotinh="Nữ";
            }
            b.putString("Gender",giotinh);
            i.putExtras(b);
            setResult(202,i);
            finish();
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void AnhXa() {
        name=findViewById(R.id.editTextName);
        birthday=findViewById(R.id.editTextBirth);
        citizen=findViewById(R.id.editTextCCCD);
        phone=findViewById(R.id.editTextPhone);
        address=findViewById(R.id.editTextAddress);
        departure=findViewById(R.id.editTextDeparture);
        departureday=findViewById(R.id.editTextDepartureDay);
        destination=findViewById(R.id.editTextDestination);
        destinationday=findViewById(R.id.editTextDestinationDay);
        schedule=findViewById(R.id.editTextSchedule);
        health=findViewById(R.id.editTextStatus);
        genderNam=findViewById(R.id.radioButtonNam);
        genderNu=findViewById(R.id.radioButtonNu);
        btnEdit=findViewById(R.id.buttonEdit);
        btnCancel=findViewById(R.id.buttonCancel);

    }
}