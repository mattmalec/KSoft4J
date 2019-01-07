<<<<<<< HEAD:src/main/java/net/explodingbush/ksoftapi/entities/UnbanEvent.java
package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.KSoftAPI;
=======
package net.explodingbush.ksoftapi.entities.events;

import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.entities.Ban;
>>>>>>> development:src/main/java/net/explodingbush/ksoftapi/entities/events/UnbanEvent.java

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
