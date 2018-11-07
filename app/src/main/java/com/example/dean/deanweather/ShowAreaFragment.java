package com.example.dean.deanweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dean.deanweather.db.WeatherCities;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class ShowAreaFragment extends Fragment {

    private Button backButton;

    private ListView listView;

    private ArrayAdapter<String> adapter;

    private List<String> dataList = new ArrayList<>();


    private WeatherCities selectedWeatherCity;

    /**
     * 查询过的城市
     */
    private List<WeatherCities> weatherCitiesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_area, container, false);
        backButton = (Button) view.findViewById(R.id.back_button);
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                clear(getContext());
                selectedWeatherCity = weatherCitiesList.get(position);
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                intent.putExtra("weather_id", selectedWeatherCity.getWeatherId());
                startActivity(intent);
                getActivity().finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        queryWeatherCities();
    }

    public void clear(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String weatherString = prefs.getString("weather", null);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }

    private void queryWeatherCities() {
        weatherCitiesList = DataSupport.findAll(WeatherCities.class);

        if (weatherCitiesList.size() > 0) {
            dataList.clear();
            for (WeatherCities weatherCities : weatherCitiesList) {
                dataList.add(weatherCities.getCountyName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
        }
    }
}
