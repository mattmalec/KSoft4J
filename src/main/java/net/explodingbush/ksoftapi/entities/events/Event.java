<<<<<<< HEAD:src/main/java/net/explodingbush/ksoftapi/entities/Event.java
package net.explodingbush.ksoftapi.entities;
=======
package net.explodingbush.ksoftapi.entities.events;
>>>>>>> development:src/main/java/net/explodingbush/ksoftapi/entities/events/Event.java

import net.explodingbush.ksoftapi.KSoftAPI;

public abstract class Event {

    private final KSoftAPI api;

    public Event(KSoftAPI api) {
        this.api = api;
    }
    public KSoftAPI getKSoftAPI() {
        return api;
    }
}
