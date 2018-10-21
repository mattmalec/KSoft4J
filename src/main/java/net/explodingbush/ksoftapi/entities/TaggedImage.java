package net.explodingbush.ksoftapi.entities;

public interface TaggedImage {

    /**
     * The URL of an image
     *
     * @return The corresponding URl of the image
     */
    String getUrl();

    /**
     * The Snowflake of an image
     *
     * @return The corresponding Snowflake of the image
     */
    String getSnowflake();

    /**
     * If an image is NSFW
     *
     * @return If the image is an NSFW image
     */
    boolean isNsfw();

    /**
     * The tag of an image
     *
     * @return The tag of the image
     */
    String getTag();


}
