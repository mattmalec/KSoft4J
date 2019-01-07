<<<<<<< HEAD:src/main/java/net/explodingbush/ksoftapi/entities/VoteEvent.java
package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.KSoftAPI;
=======
package net.explodingbush.ksoftapi.entities.events;

import net.explodingbush.ksoftapi.KSoftAPI;
import net.explodingbush.ksoftapi.entities.Vote;
>>>>>>> development:src/main/java/net/explodingbush/ksoftapi/entities/events/VoteEvent.java

public class VoteEvent extends Event {

    private final Vote vote;

    public VoteEvent(KSoftAPI api, Vote vote) {
        super(api);
        this.vote = vote;
    }

    public Vote getVote() {
        return vote;
    }
}
