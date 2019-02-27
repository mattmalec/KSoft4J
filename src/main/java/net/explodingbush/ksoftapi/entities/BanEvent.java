package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.KSoftAPI;

public class BanEvent extends Event {

    private final Ban ban;

    public BanEvent(KSoftAPI api, Ban ban) {
        super(api);
        this.ban = ban;
    }

    public Ban getBan() {
        return ban;
    }
}
