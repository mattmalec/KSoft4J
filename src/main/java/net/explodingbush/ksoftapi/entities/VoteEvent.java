package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.KSoftAPI;

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
