package net.explodingbush.ksoftapi;

import net.explodingbush.ksoftapi.entities.BanAction;
import net.explodingbush.ksoftapi.entities.RedditAction;
import net.explodingbush.ksoftapi.entities.TaggedImageAction;
import net.explodingbush.ksoftapi.entities.WikihowAction;
import net.explodingbush.ksoftapi.enums.ImageTag;
import net.explodingbush.ksoftapi.enums.ImageType;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.utils.Checks;

public class KSoftAPI {

    private final String token;
    private String request;

    public KSoftAPI(String token) {
    	Checks.notNull(token, "token");
        this.token = token;
    }

    /**
     * This returns a {@link net.explodingbush.ksoftapi.entities.RedditAction RedditAction} using the declared {@link net.explodingbush.ksoftapi.enums.ImageType ImageTypes}
     * @param type
     * The {@link ImageType ImageType} you'd like to use
     * @return {@link net.explodingbush.ksoftapi.entities.RedditAction RedditAction}s
     */
    public RedditAction getRedditImage(ImageType type) {
    	Checks.notNull(type, "type");
        switch (type) {
            case RANDOM_MEME:
                this.request = Routes.REDDIT + "random-meme";
                break;
            case RANDOW_AWW:
                this.request = Routes.REDDIT + "random-aww";
                break;
            case RANDOM_NSFW:
                this.request = Routes.REDDIT + "random-nsfw";
                break;
            case RANDOM_REDDIT:
                this.request = Routes.REDDIT + "rand-reddit";
        }
        return new RedditAction(token, type, request);
    }

    /**
     * This returns a {@link net.explodingbush.ksoftapi.entities.TaggedImageAction TaggedImageAction} using the declared {@link net.explodingbush.ksoftapi.enums.ImageTag ImageTags}
     * @param tag
     * The {@link ImageTag ImageTag} you'd like to use
     * @return {@link net.explodingbush.ksoftapi.entities.TaggedImageAction TaggedImageAction}
     */
    public TaggedImageAction getTaggedImage(ImageTag tag) {
    	Checks.notNull(tag, "tag");
        return new TaggedImageAction(token, tag);
    }
    /**
     * This returns a {@link net.explodingbush.ksoftapi.entities.WikihowAction WikihowAction}
     *
     * @return {@link net.explodingbush.ksoftapi.entities.WikihowAction WikihowAction}
     */
    public WikihowAction getRandomWikihow() {
        return new WikihowAction(token);
    }

    /**
     * This returns a {@link net.explodingbush.ksoftapi.entities.BanAction BanAction}
     *
     * @return {@link net.explodingbush.ksoftapi.entities.BanAction BanAction}
     */
    public BanAction getBan() {
        return new BanAction(token);
    }
}
