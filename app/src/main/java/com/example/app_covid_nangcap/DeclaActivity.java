package com.example.app_covid_nangcap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class DeclaActivity extends AppCompatActivity {

    EditText name,birthday,citizen,phone,address,departure,departureday,destination,destinationday,schedule,health;
    RadioButton genderNam,genderNu;
    Button btnDeclare,btnList;
    String giotinh;
    public static Sqlite db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        getSupportActionBar().setTitle("Affected Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        AnhXa();
        db = new Sqlite(this, "Device.sqlite", null, 1);

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
        btnDeclare=findViewById(R.id.buttonDeclare);
        btnList=findViewById(R.id.buttonList);

    }
    public void goList(View view) {
        startActivity(new Intent(getApplicationContext(), ListActivity.class));

    }
    public void goDeclare(View view) {
        String ten=name.getText().toString();
        String ngaysinh=birthday.getText().toString();
        String cc=citizen.getText().toString();
        String sdt=phone.getText().toString();
        String diachi=address.getText().toString();
        String noidi=departure.getText().toString();
        String ngaydi=departureday.getText().toString();
        String noiden=destination.getText().toString();
        String ngayden=destinationday.getText().toString();
        String lichtrinh=schedule.getText().toString();
        String suckhoe=health.getText().toString();
        if (genderNam.isChecked()){
            giotinh="Nam";
        }
        if (genderNu.isChecked()){
            giotinh="Nữ";
        }
        if (ten.equals("") || ngaysinh.equals("") || giotinh.equals("") || cc.equals("") || sdt.equals("") || diachi.equals("") || ngaydi.equals("") || noidi.equals("") || noiden.equals("") || ngayden.equals("") || lichtrinh.equals("") || suckhoe.equals(""))
        {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            db.addHoaDon(new InforModel(0,ten,ngaysinh,giotinh,cc,sdt,diachi,noidi,ngaydi,noiden,ngayden,lichtrinh,suckhoe));
            Toast.makeText(this, "Khai báo thành công", Toast.LENGTH_LONG).show();
            name.setText("");
            birthday.setText("");
            genderNam.setChecked(false);
            genderNu.setChecked(false);
            citizen.setText("");
            phone.setText("");
            address.setText("");
            departure.setText("");
            departureday.setText("");
            destination.setText("");
            destinationday.setText("");
            schedule.setText("");
            health.setText("");
        }



    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}