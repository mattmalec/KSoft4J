package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.KSoftAction;
import net.explodingbush.ksoftapi.entities.impl.KumoCurrentlyImpl;
import net.explodingbush.ksoftapi.entities.impl.KumoHourlyImpl;
import net.explodingbush.ksoftapi.entities.impl.KumoMinutelyImpl;
import net.explodingbush.ksoftapi.enums.*;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import okhttp3.Response;
import org.json.JSONObject;

public class KumoWeatherAction implements KSoftAction<KumoWeather> {

    private String token;
    private String locationQuery;
    private String unit = "auto";
    private String language = "en";
    private ReportType reportType = ReportType.CURRENTLY;
    private KumoType kumoType;

    public KumoWeatherAction(KumoType kumoType, String token) {
        this.token = token;
        this.kumoType = kumoType;
    }

    public KumoWeatherAction setLocationQuery(String query) {
        this.locationQuery = query;
        return this;
    }

    public KumoWeatherAction setUnits(Units unit) {
        this.unit = unit.toString().toLowerCase();
        return this;
    }

    public KumoWeatherAction setLanguage(Language language) {
        this.language = language.toString().toLowerCase();
        return this;
    }

    public KumoWeatherAction setReportType(ReportType reportType) {
        this.reportType = reportType;
        return this;
    }


    @Override
    public KumoWeather execute() {
        Response response = new JSONBuilder().requestKsoftResponse(String.format(Routes.KUMO.toString(), reportType.toString().toLowerCase(), locationQuery, unit, language), token);
        if(response.code() == 500) {
            throw new NotFoundException("NANI broke something. Everything is currently being exploded");
        }
        JSONObject json = new JSONBuilder().getJSONResponse(response);
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        if(json.has("code") && json.getInt("code") == 404) {
            throw new NotFoundException("The provided location was not found");
        }
        switch(reportType) {
            case CURRENTLY: return new KumoCurrentlyImpl(json);
            case HOURLY: return new KumoHourlyImpl(json);
            case MINUTELY: return new KumoMinutelyImpl(json);
        }
        return null;
    }
}
