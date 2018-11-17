package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.KSoftAPI;

public class UnbanEvent extends Event {

    private final Ban ban;

    public UnbanEvent(KSoftAPI api, Ban ban) {
        super(api);
        this.ban = ban;
    }

    public Ban getBan() {
        return ban;
    }
}
