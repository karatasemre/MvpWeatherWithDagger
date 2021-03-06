package mvpweatherwithdagger.com.mvpweatherwithdagger.data.api;

import com.oneframe.android.networking.NetworkConfig;
import com.oneframe.android.networking.NetworkManager;
import com.oneframe.android.networking.NetworkingFactory;
import com.oneframe.android.networking.listener.NetworkResponseListener;

import mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.weather.WeatherResponse;

import static mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.ConstantMethodSL.GET_WEATHER_INFOS;

public class WeatherServiceManager {
    NetworkManager networkManager;
    public static final String RESULT_TAG = "Data";
    public static String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";

    public WeatherServiceManager() {
        networkManager = NetworkingFactory.create();
        NetworkConfig.getInstance().setURL(WEATHER_URL);
        NetworkConfig.getInstance().getDefaultHeaders().put("Cache-Control", "no-cache");
        networkManager.setNetworkLearning(new NetworkLearning());
    }

    public void getWeatherInfos(String cityName, final NetworkResponseListener<WeatherResponse, String> listener) {
        NetworkConfig.getInstance().setURL(WEATHER_URL);
        networkManager.get(String.format((GET_WEATHER_INFOS), cityName), listener);
    }
}
