package com.example.app_covid_nangcap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.leo.simplearcloader.SimpleArcLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    ListAdapder adapter;
    EditText edtSearch;
    ArrayList<InforModel> hoadons;
    public static Sqlite db;
    int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getSupportActionBar().setTitle("Declared Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.listViewInfor);
        edtSearch=findViewById(R.id.edtSearch);
        registerForContextMenu(listView);
        db = new Sqlite(this, "Device.sqlite", null, 1);
        hoadons = db.getAll();
        adapter = new ListAdapder(this, R.layout.dong_list_view, hoadons);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectedID = position;
                return false;
            }
        });
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String key = edtSearch.getText().toString();
                Log.d("key",key);
                adapter.filter(key);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.edit:
                Intent intent = new Intent(ListActivity.this, EditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Id", hoadons.get(selectedID).getId());
                bundle.putString("Name", hoadons.get(selectedID).getName());
                bundle.putString("Birthday", hoadons.get(selectedID).getPhone());
                bundle.putString("Gender", hoadons.get(selectedID).getGender());
                bundle.putString("Citizen", hoadons.get(selectedID).getPhone());
                bundle.putString("Phone", hoadons.get(selectedID).getPhone());
                bundle.putString("Address", hoadons.get(selectedID).getPhone());
                bundle.putString("Departure", hoadons.get(selectedID).getPhone());
                bundle.putString("Departureday", hoadons.get(selectedID).getPhone());
                bundle.putString("Destination", hoadons.get(selectedID).getPhone());
                bundle.putString("Destinationday", hoadons.get(selectedID).getPhone());
                bundle.putString("Schedule", hoadons.get(selectedID).getPhone());
                bundle.putString("Health", hoadons.get(selectedID).getPhone());
                intent.putExtras(bundle);
                startActivityForResult(intent, 100);
                break;
            case R.id.delete:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Confirm");
                b.setMessage("You wants to delete?");
                b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.deleteHoaDon(hoadons.get(selectedID).getId());
                        hoadons.remove(selectedID);
                        adapter.notifyDataSetChanged();
                        listView.setAdapter(adapter);
                        dialog.cancel();
                    }
                });
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("Id");
        String ten = bundle.getString("Name");
        String ngaysinh = bundle.getString("Birthday");
        String gioitinh = bundle.getString("Gender");
        String cc = bundle.getString("Citizen");
        String sdt = bundle.getString("Phone");
        String diachi = bundle.getString("Address");
        String noidi = bundle.getString("Departure");
        String ngaydi = bundle.getString("Departureday");
        String noiden = bundle.getString("Destination");
        String ngayden = bundle.getString("Destinationday");
        String lichtrinh = bundle.getString("Schedule");
        String suckhoe = bundle.getString("Health");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 202) {

            db.editHoaDon(hoadons.get(selectedID).getId(), new InforModel(id, ten, ngaysinh, gioitinh, cc, sdt, diachi, noidi, ngaydi, noiden, ngayden, lichtrinh, suckhoe));
            hoadons.set(selectedID, new InforModel(id, ten, ngaysinh, gioitinh, cc, sdt, diachi, noidi, ngaydi, noiden, ngayden, lichtrinh, suckhoe));


        }
        Collections.sort(hoadons, new Comparator<InforModel>() {
            @Override
            public int compare(InforModel o1, InforModel o2) {
                return Integer.valueOf(o1.getId()).compareTo(o2.getId());
            }
        });
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }
}