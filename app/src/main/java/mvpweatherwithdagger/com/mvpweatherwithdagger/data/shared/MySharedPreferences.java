package mvpweatherwithdagger.com.mvpweatherwithdagger.data.shared;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.newCountry.CountryModel;

/**
 * Created by Emre.Karatas on 8.11.2018.
 */

public class MySharedPreferences {
    private SharedPreferences mSharedPreferences;

    private String CITY_NAME = "city_name";
    private String CURRENT_CITY = "CURRENT_CITY";
    private String CITY_POS = "city_pos";
    public String GET_ALL_COUNTRIES = "GET_ALL_COUNTRIES";

    @Inject
    public MySharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public String getSelectedCityName() {
        return mSharedPreferences.getString(CITY_NAME, "Turkey");
    }

    public void setSelectedCityName(String cityName) {
        mSharedPreferences.edit().putString(CITY_NAME, cityName).apply();
    }

    public int getSelectedCityPos() {
        return mSharedPreferences.getInt(CITY_POS, 0);
    }

    public void setSelectedCityPos(int cityPos) {
        mSharedPreferences.edit().putInt(CITY_POS, cityPos).apply();
    }

    public void setCountries(List<CountryModel> resultList) {
        Gson gson = new Gson();
        String json = gson.toJson(resultList);
        mSharedPreferences.edit().putString(GET_ALL_COUNTRIES, json).apply();
    }

    public List<CountryModel> getCountries() {
        Gson gson = new Gson();
        return gson.fromJson(mSharedPreferences.getString(GET_ALL_COUNTRIES, ""), new TypeToken<ArrayList<CountryModel>>() {
        }.getType());
    }

    public String getCurrentCity() {
        return mSharedPreferences.getString(CURRENT_CITY, "Turkey");
    }

    public void setCurrentCity(String cityName) {
        mSharedPreferences.edit().putString(CURRENT_CITY, cityName).apply();
    }

}
