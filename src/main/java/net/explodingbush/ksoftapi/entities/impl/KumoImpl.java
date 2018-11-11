package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Alert;
import net.explodingbush.ksoftapi.entities.Kumo;
import net.explodingbush.ksoftapi.entities.Location;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KumoImpl implements Kumo {

    private JSONObject json;

    public KumoImpl(JSONObject json) {
        this.json = json.getJSONObject("data");
    }

    @Override
    public OffsetDateTime getTime() {
        return LocalDateTime.parse(json.getString("time"), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atOffset(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
    }

    @Override
    public String getSummary() {
        return json.getString("summary");
    }

    @Override
    public String getIcon() {
        return json.getString("icon");
    }

    @Override
    public String getIconUrl() {
        return json.getString("icon_url");
    }

    @Override
    public int getPrecipIntensity() {
        return json.getInt("precipIntensity");
    }

    @Override
    public int getPrecipProbability() {
        return json.getInt("precipProbability");
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

    @Override
    public List<Alert> getAlerts() {
        List<Alert> alerts = new ArrayList<>();
        for(Object o : json.getJSONArray("alerts")) {
            JSONObject obj = new JSONObject(o.toString());
            Alert alert = new AlertImpl(obj);
            alerts.add(alert);
        }
        return Collections.unmodifiableList(alerts);
    }

    @Override
    public Location getLocation() {
        return new LocationImpl(json.getJSONObject("location"));
    }
}
