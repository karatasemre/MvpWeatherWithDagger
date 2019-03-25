package mvpweatherwithdagger.com.mvpweatherwithdagger.data.api;

import com.oneframe.android.networking.NetworkConfig;
import com.oneframe.android.networking.NetworkManager;
import com.oneframe.android.networking.NetworkingFactory;
import com.oneframe.android.networking.listener.NetworkResponseListener;

import java.util.List;

import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.newCountry.CountryModel;

import static mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.ConstantMethodSL.GET_COUNTRIES;

public class CountriesServiceManager {
    NetworkManager networkManager;
    public static final String RESULT_TAG = "Data";
    public static String COUNTRIES_URL = "https://restcountries.eu/";

    public CountriesServiceManager() {
        networkManager = NetworkingFactory.create();
        NetworkConfig.getInstance().getDefaultHeaders().put("Cache-Control", "no-cache");
        networkManager.setNetworkLearning(new NetworkLearning());
    }

    public void getCountries(final NetworkResponseListener<List<CountryModel>, String> listener) {
        NetworkConfig.getInstance().setURL(COUNTRIES_URL);
        networkManager.get(GET_COUNTRIES, listener);
    }

}
