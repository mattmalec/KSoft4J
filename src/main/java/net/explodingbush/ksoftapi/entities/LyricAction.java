package net.explodingbush.ksoftapi.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import net.explodingbush.ksoftapi.KSoftActionAdapter;
import net.explodingbush.ksoftapi.entities.lyrics.LyricCache;
import net.explodingbush.ksoftapi.entities.lyrics.Track;
import net.explodingbush.ksoftapi.entities.lyrics.impl.TrackImpl;
import net.explodingbush.ksoftapi.enums.Routes;
import net.explodingbush.ksoftapi.exceptions.LoginException;
import net.explodingbush.ksoftapi.exceptions.NotFoundException;
import net.explodingbush.ksoftapi.utils.Checks;
import net.explodingbush.ksoftapi.utils.JSONBuilder;

public class LyricAction extends KSoftActionAdapter<List<Track>>{

	private int limit;
	private String query;
	private String token;
	private boolean textOnly;
	
	private LyricCache cache;
	
	public LyricAction(String token, LyricCache cache){
		Checks.notNull(token, "token");
		this.cache = cache;
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
		this.limit = limit > 20 ? 20 : limit;
		return this;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Track> execute() {
		if(!cache.getTracksByName(query).isEmpty()) {
			return cache.getTracksByName(query);
		}
		JSONObject json;
		json = new JSONBuilder().requestKsoft(String.format(Routes.LYRICS_SEARCH.toString(), query, Boolean.toString(textOnly), limit), token);
		System.out.println(json);
        if (json.getInt("total") == 0) {
        	throw new NotFoundException("No lyrics were found that match the query.");
        }
        if (token.isEmpty() || !json.isNull("detail") && json.getString("detail").equalsIgnoreCase("Invalid token.")) {
            throw new LoginException();
        }
        List<Track> lyrics = new ArrayList<>();
        List<Object> lyricObjs = json.getJSONArray("data").toList();
        if(lyricObjs.isEmpty()){
        	throw new NotFoundException("No lyrics were found that match the query.");
        }
        for(Object obj : lyricObjs){
        	lyrics.add(new TrackImpl(new JSONObject((java.util.HashMap<String, Object>)obj), cache));
        }
		return Collections.unmodifiableList(lyrics);
	}
}
