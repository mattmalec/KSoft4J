package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.KSoftAction;
import net.explodingbush.ksoftapi.entities.impl.KumoImpl;
import net.explodingbush.ksoftapi.enums.Language;
import net.explodingbush.ksoftapi.enums.ReportType;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.enums.Units;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import okhttp3.Response;
import org.json.JSONObject;

public class KumoAction implements KSoftAction<Kumo> {

    private String token;
    private String locationQuery;
    private String unit = "auto";
    private String language = "en";
    private String reportType = "currently";

    public KumoAction(String token) {
        this.token = token;
    }

    public KumoAction setLocationQuery(String query) {
        this.locationQuery = query;
        return this;
    }

    public KumoAction setUnits(Units unit) {
        this.unit = unit.toString().toLowerCase();
        return this;
    }

    public KumoAction setLanguage(Language language) {
        this.language = language.toString().toLowerCase();
        return this;
    }

    public KumoAction setReportType(ReportType reportType) {
        this.reportType = reportType.toString().toLowerCase();
        return this;
    }

    @Override
    public Kumo execute() {
        Response response = new JSONBuilder().requestKsoftResponse(String.format(Routes.KUMO.toString(), reportType, locationQuery, unit, language), token);
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
        System.out.println(json.toString());
        return new KumoImpl(json);
    }
}
