package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Alert;
import net.explodingbush.ksoftapi.entities.KumoWeather;
import net.explodingbush.ksoftapi.entities.Location;
import net.explodingbush.ksoftapi.enums.Units;

import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KumoMinutelyImpl extends KumoImpl implements KumoWeather {


    private JSONObject json;

    @Override
    public String getSummary() {
        return null;
    }

    @Override
    public float getTemperature() {
        return 0;
    }

    @Override
    public float getApparentTemperature() {
        return 0;
    }

    @Override
    public String getIcon() {
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
    public float getDewPoint() {
        return 0;
    }

    @Override
    public float getHumidity() {
        return 0;
    }

    @Override
    public float getPressure() {
        return 0;
    }

    @Override
    public int getNearestStormDistance() {
        return 0;
    }

    @Override
    public int getNearestStormBearing() {
        return 0;
    }

    @Override
    public float getWindSpeed() {
        return 0;
    }

    @Override
    public float getWindGust() {
        return 0;
    }

    @Override
    public int getWindBearing() {
        return 0;
    }

    @Override
    public float getCloudCover() {
        return 0;
    }

    @Override
    public int getUvIndex() {
        return 0;
    }

    @Override
    public float getVisibility() {
        return 0;
    }

    @Override
    public float getOzone() {
        return 0;
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

    @Override
    public List<KumoWeather> getAll() {
        List<KumoWeather> hourlyList = new ArrayList<>();
        for(Object o : json.getJSONObject("data").getJSONArray("by_minute")) {
            KumoWeather minutely = new KumoMinutelyImpl(new JSONObject(o.toString()));
            hourlyList.add(minutely);
        }
        return Collections.unmodifiableList(hourlyList);
    }

    public KumoMinutelyImpl(JSONObject json) {
        super(json);
        this.json = json;
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
    public String getIconUrl() {
        return json.getString("icon_url");
    }
	@Override
	public Units getUnits() {
		if(json.has("units")) {
			return Units.valueOf(json.getString("units").toUpperCase());
		}
		return null;
	}
}
