package mvpweatherwithdagger.com.mvpweatherwithdagger.data.api.model.weather;

import lombok.Data;

/**
 * Created by Emre.Karatas on 21.03.2019.
 */
@Data
public class Weather {
    private Integer id;
    private String main;
    private String description;
    private String icon;

}
