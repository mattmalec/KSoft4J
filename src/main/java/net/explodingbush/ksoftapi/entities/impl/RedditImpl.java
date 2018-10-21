package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Reddit;
import org.json.JSONObject;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class RedditImpl implements Reddit {

    private final JSONObject json;
    public RedditImpl(JSONObject json) {
        this.json = json;
    }

    @Override
    public String getTitle() {
        return json.getString("title");
    }

    @Override
    public String getImageUrl() {
        return json.getString("image_url");
    }

    @Override
    public String getSourceUrl() {
        return json.getString("source");
    }

    @Override
    public String getSubreddit() {
        return json.getString("subreddit");
    }

    @Override
    public String getUpvotes() {
        return String.valueOf(json.getInt("upvotes"));
    }

    @Override
    public int getUpvotesInt() {
        return json.getInt("upvotes");
    }

    @Override
    public boolean subredditExists() {
        return !json.isNull("subreddit");
    }

    @Override
    public String getDownvotes() {
        return String.valueOf(json.getInt("downvotes"));
    }

    @Override
    public int getDownvotesInt() {
        return json.getInt("downvotes");
    }

    @Override
    public OffsetDateTime getCreationTime() {
        long time = json.getLong("created_at");
        return Instant.ofEpochSecond(time).atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    @Override
    public boolean isNsfw() {
        return json.getBoolean("nsfw");
    }

    @Override
    public String getAuthor() {
        return json.getString("author");
    }
}
