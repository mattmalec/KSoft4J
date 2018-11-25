package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.KumoIP;
import org.json.JSONObject;

public class KumoIPImpl implements KumoIP {

    private JSONObject json;

    public KumoIPImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public String getCity() {
        return json.getString("city");
    }

    @Override
    public String getContinentCode() {
        return json.getString("continent_code");
    }

    @Override
    public String getContinentName() {
        return json.getString("continent_name");
    }

    @Override
    public String getCountryCode() {
        return json.getString("country_code");
    }

    @Override
    public String getCountryName() {
        return json.getString("country_name");
    }

    @Override
    public double getLatitude() {
        return json.getDouble("latitude");
    }

    @Override
    public double getLongitude() {
        return json.getDouble("longitude");
    }

    @Override
    public String getPostalCode() {
        return json.getString("postal_code");
    }

    @Override
    public String getRegion() {
        return json.getString("region");
    }

    @Override
    public String getTimeZone() {
        return json.getString("time_zone");
    }
}
