package net.explodingbush.ksoftapi.entities;

import net.explodingbush.ksoftapi.KSoftAction;
import net.explodingbush.ksoftapi.entities.impl.AddBanImpl;
import net.explodingbush.ksoftapi.entities.impl.BanImpl;
import net.explodingbush.ksoftapi.entities.impl.BulkBanImpl;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.exceptions.AddBanException;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.MissingArgumentException;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import org.json.JSONObject;

/**
 * Used to interface with {@link AddBan AddBan} and {@link Ban Ban}
 * <h1>Example</h1>
 * <h2>Getting a ban by ID</h2>
 * <code>Ban ban = new KSoftAPI(token).getBan().setUserId("492811511081861130");</code>
 * <br>
 * <code>System.out.println(ban.getName());</code>
 * <br>
 * <h2>Reporting a ban</h2>
 * <code>AddBan ban = new KSoftAPI(token).getBan().addBan();</code>
 * <br>
 * <code>ban.setUserId("123").setReason("Bad boy").setProof("nani is bad proof").set().execute();</code>
 */

public class BanAction implements KSoftAction<Ban> {


    private String banId;
    private static String tokenValue;
    private int results;
    private boolean isAddingBan;
    private JSONObject banJson;
    private boolean isBulkChecking;
    public BanAction(String token) {
        tokenValue = token;
    }
    public BanAction(boolean isAddingBan, boolean isBulkChecking, JSONObject banJson) {
    	Checks.notNull(banJson, "banJson");
        this.isAddingBan = isAddingBan;
        this.isBulkChecking = isBulkChecking;
        this.banJson = banJson;
    }

    /**
     * Sets the amount of bans to retrieve
     * @param results
     *  The amount of results to use with an {@link Ban#getBannedList() Ban#getBannedList()} and/or a {@link Ban#getBannedStream() Ban#getBannedStream()}
     * @return BanAction instance. Useful for chaining.
     */
    public BanAction getBanList(int results) {
    	Checks.positive(results, "results");
        this.results = results;
        return this;
    }
    /**
     * Sets the amount of bans to retrieve
     * @param userId
     *  The Discord user ID to use when retrieving an individual ban
     * @return BanAction instance. Useful for chaining.
     */
    public BanAction setUserId(String userId) {
    	Checks.notNull(userId, "userId");
        this.banId = userId;
        return this;
    }
    /**
     * Returns a new {@link AddBan} instance to report a new ban
     * @return A new chainable {@link AddBan} instance
     */
    public AddBan addBan() {
        return new AddBanImpl();
    }

    public BulkBan checkBulkBan() {
        return new BulkBanImpl();
    }
    /**
     * Executes the request with the specified parameters
     *
     * @return A new {@link Ban Ban} instance.
     * @throws net.explodingbush.ksoftapi.exceptions.LoginException           If the token is not provided or incorrect.
     * @throws net.explodingbush.ksoftapi.exceptions.MissingArgumentException If using {@link net.explodingbush.ksoftapi.entities.BanAction#getBanList(int) BanAction#getBanList} and the value is invalid
     */
    @Override
    public Ban execute() throws LoginException {
        JSONObject json;
        if(isAddingBan) {
            json = banJson;
            JSONObject newJson = new JSONBuilder().addBanKsoft(banJson, Routes.BAN_ADD, tokenValue);
            if(newJson.has("error")) {
                throw new AddBanException(newJson.getString("message"));
            }
        } else if(isBulkChecking) {
            json = banJson;
            JSONObject array = new JSONBuilder().bulkBanCheck(json, tokenValue, Routes.BAN_BULK);
            json = array;
        } else {
            if (results == 0 && banId == null) {
                throw new MissingArgumentException("Missing action value. Could not be parsed");
            }
            if (results == 0) {
                json = new JSONBuilder().requestKsoft(Routes.BAN_INFO + banId, tokenValue);
            } else {
                json = new JSONBuilder().requestKsoft(Routes.BAN_LIST + "" + results, tokenValue);
            }
        }
        if (tokenValue.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.") || !json.has("data")) {
            throw new LoginException();
        }
        return new BanImpl(json);
    }
}
