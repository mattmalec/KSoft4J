package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.AddBan;
import net.explodingbush.ksoftapi.entities.BanAction;
import net.explodingbush.ksoftapi.exceptions.MissingArgumentException;
import org.json.JSONObject;

public class AddBanImpl implements AddBan {

    private JSONObject json = new JSONObject();

    @Override
    public AddBan setUserId(String user) {
        json.put("user", user);
        return this;
    }

    @Override
    public AddBan setModeratorId(String moderatorId) {
        json.put("mod", moderatorId);
        return this;
    }

    @Override
    public AddBan setUsername(String userName) {
        json.put("user_name", userName);
        return this;
    }

    @Override
    public AddBan setDiscriminator(String discriminator) {
        json.put("user_discriminator", discriminator);
        return this;
    }

    @Override
    public AddBan setReason(String reason) {
        json.put("reason", reason);
        return this;
    }

    @Override
    public AddBan setProof(String proof) {
        json.put("proof", proof);
        return this;
    }

    @Override
    public String toString() {
        return json.toString();
    }

    @Override
    public AddBan setAppealable(boolean appealable) {
        json.put("appeal_possible", String.valueOf(appealable));
        return this;
    }

    @Override
    public BanAction set() {
        if(json.isNull("user")) {
            throw new MissingArgumentException("setUserId cannot be null");
        }
        if(json.isNull("reason")) {
            throw new MissingArgumentException("setReason cannot be null");
        }
        if(json.isNull("proof")) {
            throw new MissingArgumentException("setProof cannot be null");
        }
        if(json.isNull("user_name")) {
            throw new MissingArgumentException("setUsername cannot be null");
        }
        if(json.isNull("user_discriminator")) {
            throw new MissingArgumentException("setDiscriminator cannot be null");
        }
        return new BanAction(true, json);
    }
}


