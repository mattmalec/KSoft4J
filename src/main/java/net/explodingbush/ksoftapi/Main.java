package net.explodingbush.ksoftapi;

import net.explodingbush.ksoftapi.entities.Kumo;
import net.explodingbush.ksoftapi.enums.ReportType;

public class Main {
    public static void main(String[] args) {
        KSoftAPI api = new KSoftAPI("27ad9320ead0dba5c43bf00856256f88bbe30024");
        Kumo kumo = api.getKumo().setLocationQuery("syracuse").setReportType(ReportType.CURRENTLY).execute();
        kumo.getAlerts().forEach(alert -> System.out.printf("\n\nTitle: %s\nRegions: %s\nTime: %s\nExpires: %s\nDescription: %s", alert.getTitle(), alert.getRegions(), alert.getTime(), alert.getExpiryTime(), alert.getDescription()));
    }
}
