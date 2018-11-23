package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.BanAction;
import net.explodingbush.ksoftapi.entities.BulkBan;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BulkBanImpl implements BulkBan {

    private List<String> ids = new ArrayList<>();

    @Override
    public BulkBan addId(String id) {
        ids.add(id);
        return this;
    }

    @Override
    public BulkBan removeId(String id) {
        ids.remove(id);
        return this;
    }

    @Override
    public String toString() {
        return ids.toString();
    }

    @Override
    public BanAction set() {
        JSONObject json = new JSONObject();
        json.put("users", ids);
        return new BanAction(false, true, json);
    }
}
