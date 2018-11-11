package net.explodingbush.ksoftapi.entities.impl;

import net.explodingbush.ksoftapi.entities.Reddit;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import net.explodingbush.ksoftapi.utils.Checks;

import org.json.JSONObject;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

public class RedditImpl implements Reddit {

    private final JSONObject json;
    public RedditImpl(JSONObject json) {
    	Checks.notNull(json, "json");
        this.json = json;
    }

    @Override
    public String getTitle() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        return json.getString("title");
    }

    @Override
    public String getImageUrl() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        return json.getString("image_url");
    }

    @Override
    public String getSourceUrl() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        return json.getString("source");
    }

    @Override
    public String getSubreddit() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        return json.getString("subreddit");
    }

    @Override
    public String getUpvotes() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        return String.valueOf(json.getInt("upvotes"));
    }

    @Override
    public int getUpvotesInt() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        return json.getInt("upvotes");
    }

    @Override
    public boolean subredditExists() {
        return !json.isNull("subreddit");
    }

    @Override
    public String getDownvotes() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        return String.valueOf(json.getInt("downvotes"));
    }

    @Override
    public int getDownvotesInt() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        return json.getInt("downvotes");
    }

    @Override
    public OffsetDateTime getCreationTime() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        long time = json.getLong("created_at");
        return Instant.ofEpochSecond(time).atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }

    @Override
    public boolean isNsfw() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        return json.getBoolean("nsfw");
    }

    @Override
    public String getAuthor() {
        if (json.has("error")) {
            throw new NotFoundException("The specified subreddit was not found.");
        }
        return json.getString("author");
    }
}
