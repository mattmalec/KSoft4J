package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Alert;
import net.explodingbush.ksoftapi.entities.KumoWeather;
import net.explodingbush.ksoftapi.entities.Location;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class KumoCurrentlyImpl extends KumoImpl implements KumoWeather {

    private JSONObject json;

    @Override
    public List<KumoWeather> getAll() {
        return null;
    }

    @Override
    public String getSummary() {
        return null;
    }

    @Override
    public String getIcon() {
        return null;
    }

    @Override
    public String getIconUrl() {
        return null;
    }

    @Override
    public OffsetDateTime getSunriseTime() {
        return null;
    }

    @Override
    public OffsetDateTime getSunsetTime() {
        return null;
    }

    @Override
    public float getMoonPhase() {
        return 0;
    }

    @Override
    public float getPrecipIntensityMax() {
        return 0;
    }

    @Override
    public OffsetDateTime getPrecipIntensityMaxTime() {
        return null;
    }

    @Override
    public float getPrecipAccumulation() {
        return 0;
    }

    @Override
    public String getPrecipType() {
        return null;
    }

    @Override
    public float getTemperatureHigh() {
        return 0;
    }

    @Override
    public OffsetDateTime getTemperatureHighTime() {
        return null;
    }

    @Override
    public float getTemperatureLow() {
        return 0;
    }

    @Override
    public OffsetDateTime getTemperatureLowTime() {
        return null;
    }

    @Override
    public float getApparentTemperatureHigh() {
        return 0;
    }

    @Override
    public OffsetDateTime getApparentTemperatureHighTime() {
        return null;
    }

    @Override
    public float getApparentTemperatureLow() {
        return 0;
    }

    @Override
    public OffsetDateTime getApparentTemperatureLowTime() {
        return null;
    }

    @Override
    public float getTemperatureMin() {
        return 0;
    }

    @Override
    public OffsetDateTime getTemperatureMinTime() {
        return null;
    }

    @Override
    public float getApparentTemperatureMin() {
        return 0;
    }

    @Override
    public OffsetDateTime getApparentTemperatureMinTime() {
        return null;
    }

    @Override
    public float getTemperatureMax() {
        return 0;
    }

    @Override
    public OffsetDateTime getTemperatureMaxTime() {
        return null;
    }

    @Override
    public float getApparentTemperatureMax() {
        return 0;
    }

    @Override
    public OffsetDateTime getApparentTemperatureMaxTime() {
        return null;
    }

    @Override
    public String getMainSummary() {
        return super.getMainSummary();
    }

    @Override
    public String getMainIcon() {
        return super.getMainIcon();
    }

    @Override
    public String getMainIconUrl() {
        return super.getMainIconUrl();
    }

    @Override
    public List<Alert> getAlerts() {
        return super.getAlerts();
    }

    @Override
    public Location getLocation() {
        return super.getLocation();
    }

    public KumoCurrentlyImpl(JSONObject json) {
        super(json.getJSONObject("data"));
        this.json = json.getJSONObject("data");
    }

    @Override
    public int getNearestStormDistance() {
        return json.getInt("nearestStormDistance");
    }

    @Override
    public int getNearestStormBearing() {
        return json.getInt("nearestStormBearing");
    }
    @Override
    public OffsetDateTime getTime() {
        return LocalDateTime.parse(json.getString("time"), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atOffset(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
    }

    @Override
    public float getPrecipIntensity() {
        return (float) json.getDouble("precipIntensity");
    }

    @Override
    public float getPrecipProbability() {
        return (float) json.getDouble("precipProbability");
    }

    @Override
    public float getTemperature() {
        return (float) json.getDouble("temperature");
    }

    @Override
    public float getApparentTemperature() {
        return (float) json.getDouble("apparentTemperature");
    }

    @Override
    public float getDewPoint() {
        return (float) json.getDouble("dewPoint");
    }

    @Override
    public float getHumidity() {
        return (float) json.getDouble("humidity");
    }

    @Override
    public float getPressure() {
        return (float) json.getDouble("pressure");
    }

    @Override
    public float getWindSpeed() {
        return (float) json.getDouble("windSpeed");
    }

    @Override
    public float getWindGust() {
        return (float) json.getDouble("windGust");
    }

    @Override
    public int getWindBearing() {
        return json.getInt("windBearing");
    }

    @Override
    public float getCloudCover() {
        return (float) json.getDouble("cloudCover");
    }

    @Override
    public int getUvIndex() {
        return json.getInt("uvIndex");
    }

    @Override
    public float getVisibility() {
        return (float) json.getDouble("visibility");
    }

    @Override
    public float getOzone() {
        return (float) json.getDouble("ozone");
    }
}
