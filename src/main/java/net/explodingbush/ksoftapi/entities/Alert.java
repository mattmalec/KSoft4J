package net.explodingbush.ksoftapi.entities;

import java.time.OffsetDateTime;
import java.util.List;

public interface Alert {

    String getTitle();
    List<String> getRegions();
    String getSeverity();
    OffsetDateTime getTime();
    OffsetDateTime getExpiryTime();
    String getDescription();
    String getURL();

}
