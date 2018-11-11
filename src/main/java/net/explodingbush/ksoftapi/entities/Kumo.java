package net.explodingbush.ksoftapi.entities;

import java.time.OffsetDateTime;
import java.util.List;

public interface Kumo {

    OffsetDateTime getTime();
    String getSummary();
    String getIcon();
    String getIconUrl();
    int getPrecipIntensity();
    int getPrecipProbability();
    float getTemperature();
    float getApparentTemperature();
    float getDewPoint();
    float getHumidity();
    float getPressure();
    float getWindSpeed();
    float getWindGust();
    int getWindBearing();
    float getCloudCover();
    int getUvIndex();
    float getVisibility();
    float getOzone();
    List<Alert> getAlerts();
    Location getLocation();

}
