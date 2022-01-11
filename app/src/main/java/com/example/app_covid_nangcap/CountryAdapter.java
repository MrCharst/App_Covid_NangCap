package com.example.app_covid_nangcap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<CountryModel> countryModelsList;
    private List<CountryModel> countryModelsListFiltered;

    public CountryAdapter(Context context, List<CountryModel> countryModelsList) {
        this.context = context;
        this.countryModelsList = countryModelsList;
        this.countryModelsListFiltered = countryModelsList;
    }
    @Override
    public int getCount() {
        return countryModelsListFiltered.size();
    }

    @Override
    public Object getItem(int i) {
        return countryModelsListFiltered.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dong_list_country,null,true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);
        CountryModel dv = countryModelsListFiltered.get(i);
        tvCountryName.setText(dv.getCountry());
        Glide.with(context).load(dv.getFlag()).into(imageView);
            return view;
    }
//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        countryModelsListFiltered.clear();
//        if (charText.length() == 0) {
//            countryModelsListFiltered.addAll(countryModelsList);
//        }
//        else
//        {
//            for (CountryModel wp : countryModelsList) {
//                if (wp.getCountry().toLowerCase(Locale.getDefault()).contains(charText)) {
//                    countryModelsListFiltered.add(wp);
//                }
//            }
//            for(CountryModel item:countryModelsListFiltered){
//                Log.d("hoten",item.getCountry());
//            }
//
//
//        }
//        notifyDataSetChanged();
//    }
        public Filter getFilter() {
        Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults filterResults = new FilterResults();
            if(constraint == null || constraint.length() == 0){
                filterResults.count = countryModelsList.size();
                filterResults.values = countryModelsList;

            }else{
                List<CountryModel> resultsModel = new ArrayList<>();
                String searchStr = constraint.toString().toLowerCase();

                for(CountryModel itemsModel:countryModelsList){
                    if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                        resultsModel.add(itemsModel);

                    }
                    filterResults.count = resultsModel.size();
                    filterResults.values = resultsModel;
                }


            }

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            countryModelsListFiltered = (List<CountryModel>) results.values;
            SearchCountry.countryModelsList = (List<CountryModel>) results.values;
            notifyDataSetChanged();

        }
    };
    return filter;
}

}

