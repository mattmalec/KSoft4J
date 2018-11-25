package net.explodingbush.ksoftapi.entities;

public interface Vote {

    String getType();
    long getBotIdLong();
    long getUserIdLong();
    String getUserId();
    String getBotId();
    boolean getIsWeekend();
    String getQuery();

}
