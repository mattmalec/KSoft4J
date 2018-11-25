package net.explodingbush.ksoftapi.hooks;

import net.explodingbush.ksoftapi.entities.Event;

@FunctionalInterface
public interface KSoftListener {

    void onEvent(Event event);

}
