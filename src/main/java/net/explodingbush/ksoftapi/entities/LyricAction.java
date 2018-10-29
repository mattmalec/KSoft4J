package net.explodingbush.ksoftapi.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.explodingbush.ksoftapi.KSoftAction;
import net.explodingbush.ksoftapi.entities.impl.LyricImpl;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;
import okhttp3.Response;

public class LyricAction implements KSoftAction<List<Lyric>>{

	private int limit;
	private String query;
	private String token;
	private boolean textOnly;
	private final Logger logger = LoggerFactory.getLogger(LyricAction.class);
	
	public LyricAction(String token){
		Checks.notNull(token, "token");
		this.token = token;
		this.limit = 10;
	}
	public LyricAction search(String query){
		Checks.notBlank(query, "query");
		this.query = query;
		return this;
	}
	public LyricAction searchLyricTextOnly(boolean textOnly){
		this.textOnly = textOnly;
		return this;
	}
	public LyricAction setLimit(int limit){
		Checks.positive(limit, "limit");
		this.limit = limit;
		return this;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Lyric> execute() {
		Response response;
		JSONObject json;
		response = new JSONBuilder().requestKsoftResponse(String.format(Routes.LYRICS.toString(), query, Boolean.toString(textOnly), limit), token);
		if (response.code() == 500 || response.code() == 404 || response.code() == 130) {
            throw new NotFoundException("No lyrics were found that match the query.");
        } else {
            json = new JSONBuilder().getJSONResponse(response);
            if (json.getInt("total") == 0) {
            	throw new NotFoundException("No lyrics were found that match the query.");
            }
        }
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        List<Lyric> lyrics = new ArrayList<>();
        List<Object> lyricObjs = json.getJSONArray("data").toList();
        if(lyricObjs.isEmpty()){
        	throw new NotFoundException("No lyrics were found that match the query.");
        }
        for(Object obj : lyricObjs){
        	lyrics.add(new LyricImpl(new JSONObject((java.util.HashMap<String, Object>)obj)));
        }
		return Collections.unmodifiableList(lyrics);
	}
}
