package com.example.app_covid_nangcap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListAdapder extends BaseAdapter {
    private Context context;
    private int layout;
    private List<InforModel> list;
    private ArrayList<InforModel> arrayList = null;

    public ListAdapder(Context context, int layout, List<InforModel> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        this.arrayList = new ArrayList<>();
        arrayList.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private class ViewHolder {
        TextView txtName, txtGioitinh,txtCCCD,txtNgaySinh,txtSDT,txtDiachi,txtNoidi,txtNoiden,txtSuckhoe;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_list_view, null);
            holder.txtName = view.findViewById(R.id.textViewName);
            holder.txtGioitinh = view.findViewById(R.id.textViewGioitinh);
            holder.txtCCCD = view.findViewById(R.id.textViewCCCD);
            holder.txtNgaySinh = view.findViewById(R.id.textViewNgaysinh);
            holder.txtSDT = view.findViewById(R.id.textViewSDT);
            holder.txtDiachi = view.findViewById(R.id.textViewDiaChi);
            holder.txtNoidi = view.findViewById(R.id.textViewNoidi);
            holder.txtNoiden = view.findViewById(R.id.textViewNoiden);
            holder.txtSuckhoe = view.findViewById(R.id.textViewSuckhoe);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        InforModel dv = list.get(i);
        holder.txtName.setText(dv.getName());
        holder.txtGioitinh.setText(dv.getGender());
        holder.txtCCCD.setText(dv.getCitizen());
        holder.txtNgaySinh.setText(dv.getBirthday());
        holder.txtSDT.setText(dv.getPhone());
        holder.txtDiachi.setText(dv.getAddress());
        holder.txtNoidi.setText(dv.getDeparture());
        holder.txtNoiden.setText(dv.getDestination());
        holder.txtSuckhoe.setText(dv.getHealth());
        return view;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(arrayList);
        }
        else
        {
            for (InforModel wp : arrayList) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(wp);
                }
            }
            for(InforModel item:list){
                Log.d("hoten",item.getName());
            }


        }
        notifyDataSetChanged();
    }
}
