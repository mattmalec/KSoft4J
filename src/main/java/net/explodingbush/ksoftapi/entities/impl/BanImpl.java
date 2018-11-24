package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Ban;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class BanImpl implements Ban {

    private final JSONObject json;
    public BanImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public long getId() {
        return json.getLong("id");
    }

    @Override
    public String getName() {
        return json.getString("name");
    }

    @Override
    public int getDiscriminator() {
        return Integer.parseInt(json.getString("discriminator"));
    }

    @Override
    public String getEffectiveName() {
        return getName() + "#" + getDiscriminator();
    }

    @Override
    public long getModId() {
    	if(json.isNull("moderator_id")){
    		throw new NotFoundException("Moderator ID not found");
    	}
        return json.getLong("moderator_id");
    }

    @Override
    public String getReason() {
        return json.getString("reason");
    }

    @Override
    public String getProof() {
        return json.getString("proof");
    }

    @Override
    public boolean isBanActive() {
        return json.getBoolean("is_ban_active");
    }

    @Override
    public boolean isAppealable() {
        return json.getBoolean("can_be_appealed");
    }


    @Override
    public OffsetDateTime getTimestamp() {
        return LocalDateTime.parse(json.getString("timestamp"), DateTimeFormatter.ISO_LOCAL_DATE_TIME).atOffset(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
    }

    @Override
    public boolean exists() {
        return json.getBoolean("exists");
    }

    @Override
    public boolean isBanned() {
        return exists();
    }

    @Override
    public List<Ban> getBannedList() {
        JSONArray array = json.getJSONArray("data");
        List<Ban> banList = new ArrayList<>();
        for(Object o : array) {
            banList.add(new BanImpl(new JSONObject(o.toString())));
        }
        return Collections.unmodifiableList(banList);
    }

    @Override
    public Stream<Ban> getBannedStream() {
        return getBannedList().stream();
    }

    @Override
    public List<Ban> getBulkBanList() {
        return getBannedList();
    }

    @Override
    public Stream<Ban> getBulkBanStream() {
        return getBannedStream();
    }

    @Override
    public String toString() {
        return json.toString();
    }
}
