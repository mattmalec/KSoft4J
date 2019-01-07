package net.explodingbush.ksoftapi.entities.actions;

import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.entities.Reddit;
import net.explodingbush.ksoftapi.entities.impl.RedditImpl;
import net.explodingbush.ksoftapi.enums.ImageType;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.MissingArgumentException;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import net.explodingbush.ksoftapi.webhooks.WebhookService;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;

public class RedditAction extends KSoftActionAdapter<Reddit> {

    private String token;
    private ImageType type;
    private String subreddit;
    private String request;
    private Logger logger = new WebhookService(null).getLogger();

    public RedditAction(String token, ImageType type, String request) {
    	Checks.notNull(token, "token");
    	Checks.notNull(type, "type");
    	Checks.notNull(request, "request");
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
    	Checks.notNull(subreddit, "subreddit");
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
        if (request == null || type.equals(ImageType.RANDOM_REDDIT) && this.subreddit == null) {
            throw new MissingArgumentException("Subreddit not defined");
        }
        if (subreddit != null && !type.equals(ImageType.RANDOM_REDDIT)) {
            logger.warn("You're setting a subreddit, but ImageType is not RANDOM_REDDIT");
        }
        if (type.equals(ImageType.RANDOM_REDDIT)) {
            request = request.concat("/" + subreddit);
        }
        response = new JSONBuilder().requestKsoftResponse(request, token);
        json = new JSONBuilder().getJSONResponse(response);
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        json = new JSONBuilder().requestKsoft(request, token);
        return new RedditImpl(json);
    }
}
