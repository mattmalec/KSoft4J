package net.explodingbush.ksoftapi.entities;

<<<<<<< HEAD:src/main/java/net/explodingbush/ksoftapi/entities/KumoWeather.java
import java.time.OffsetDateTime;
import java.util.List;

import net.explodingbush.ksoftapi.enums.Units;

public interface KumoWeather {

    List<KumoWeather> getAll();
=======
import net.explodingbush.ksoftapi.enums.PrecipType;

import java.time.OffsetDateTime;
import java.util.List;

public interface Weather {

    List<Weather> getAll();
>>>>>>> development:src/main/java/net/explodingbush/ksoftapi/entities/Weather.java
    OffsetDateTime getTime();
    String getSummary();
    float getTemperature();
    float getApparentTemperature();
    String getIcon();
    String getIconUrl();
    OffsetDateTime getSunriseTime();
    OffsetDateTime getSunsetTime();
    float getMoonPhase();
    float getPrecipIntensity();
    float getPrecipIntensityMax();
    OffsetDateTime getPrecipIntensityMaxTime();
    float getPrecipProbability();
    float getPrecipAccumulation();
<<<<<<< HEAD:src/main/java/net/explodingbush/ksoftapi/entities/KumoWeather.java
    String getPrecipType();
=======
    PrecipType getPrecipType();
>>>>>>> development:src/main/java/net/explodingbush/ksoftapi/entities/Weather.java
    float getTemperatureHigh();
    OffsetDateTime getTemperatureHighTime();
    float getTemperatureLow();
    OffsetDateTime getTemperatureLowTime();
    float getApparentTemperatureHigh();
    OffsetDateTime getApparentTemperatureHighTime();
    float getApparentTemperatureLow();
    OffsetDateTime getApparentTemperatureLowTime();
    float getTemperatureMin();
    OffsetDateTime getTemperatureMinTime();
    float getApparentTemperatureMin();
    OffsetDateTime getApparentTemperatureMinTime();
    float getTemperatureMax();
    OffsetDateTime getTemperatureMaxTime();
    float getApparentTemperatureMax();
    OffsetDateTime getApparentTemperatureMaxTime();
    float getDewPoint();
    float getHumidity();
    float getPressure();
    int getNearestStormDistance();
    int getNearestStormBearing();
    float getWindSpeed();
    float getWindGust();
    int getWindBearing();
    float getCloudCover();
    int getUvIndex();
    float getVisibility();
    float getOzone();
<<<<<<< HEAD:src/main/java/net/explodingbush/ksoftapi/entities/KumoWeather.java
    String getMainSummary();
    String getMainIcon();
    String getMainIconUrl();
    List<Alert> getAlerts();
    Location getLocation();
	Units getUnits();
=======
    List<Alert> getAlerts();
    Location getLocation();

    @Override
    String toString();
>>>>>>> development:src/main/java/net/explodingbush/ksoftapi/entities/Weather.java

}
