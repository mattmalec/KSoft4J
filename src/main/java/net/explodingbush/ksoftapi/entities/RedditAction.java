package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.enums.ImageType;
import net.explodingbush.ksoftapi.KSoftAction;
import net.explodingbush.ksoftapi.entities.impl.RedditImpl;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.MissingArgumentException;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedditAction implements KSoftAction<Reddit> {

    private String token;
    private ImageType type;
    private String subreddit;
    private String request;
    private Logger logger = LoggerFactory.getLogger(RedditAction.class);

    public RedditAction(String token, ImageType type, String request) {
        this.token = token;
        this.type = type;
        this.request = request;
    }

    /**
     * Sets the subreddit
     * @param subreddit
     *  The subreddit to use with an {@link ImageType#RANDOM_REDDIT ImageType#RANDOM_REDDIT}
     * @return RedditAction instance. Useful for chaining.
     */
    public RedditAction setSubreddit(String subreddit) {
        this.subreddit = subreddit;
        return this;
    }

    /**
     * Executes the request with the specified parameters
     *
     * @return A new {@link Reddit Reddit} instance with the provided {@link ImageType ImageTypes}
     * @throws net.explodingbush.ksoftapi.exceptions.LoginException           If the token is not provided or incorrect.
     * @throws net.explodingbush.ksoftapi.exceptions.MissingArgumentException If using {@link net.explodingbush.ksoftapi.enums.ImageType#RANDOM_REDDIT ImageType.RANDOM_REDDIT} without declaring a subreddit
     */
    @Override
    public Reddit execute() throws LoginException, MissingArgumentException {
        Response response;
        JSONObject json;
        if (request == null || type == ImageType.RANDOM_REDDIT && this.subreddit == null) {
            throw new MissingArgumentException("Subreddit not defined");
        }
        if (subreddit != null && type != ImageType.RANDOM_REDDIT) {
            logger.warn("You're setting a subreddit, but ImageType is not RANDOM_REDDIT");
        }

        if (type == ImageType.RANDOM_REDDIT) {
            request = request.concat(subreddit);
            response = new JSONBuilder().requestKsoftResponse(request, token);
            if (response.code() == 500 || response.code() == 404 || response.code() == 130) {
                throw new NotFoundException("The specified subreddit was not found.");
            } else {
                json = new JSONBuilder().getJSONResponse(response);
                if (json.has("error")) {
                    throw new NotFoundException("The specified subreddit was not found.");
                }
            }
            if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
                throw new LoginException();
            }
        }
        json = new JSONBuilder().requestKsoft(request, token);
        return new RedditImpl(json);
    }
}