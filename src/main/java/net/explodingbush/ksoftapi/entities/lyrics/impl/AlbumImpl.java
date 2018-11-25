package net.explodingbush.ksoftapi.entities.lyrics.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import net.explodingbush.ksoftapi.entities.lyrics.Album;
import net.explodingbush.ksoftapi.entities.lyrics.Artist;
import net.explodingbush.ksoftapi.entities.lyrics.LyricCache;
import net.explodingbush.ksoftapi.entities.lyrics.LyricUtil;
import net.explodingbush.ksoftapi.entities.lyrics.Track;

public class AlbumImpl implements Album{

	private JSONObject json;
	private LyricCache cache;
	
	public AlbumImpl(JSONObject json, LyricCache cache) {
		this.json = json;
		this.cache = cache;
		cache.addAlbum(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Track> getTracks() {
		List<Track> tracks = new ArrayList<>();
		JSONArray arr = json.getJSONArray("tracks");
		JSONObject tmp;
		for(Object obj : arr) {
			tmp = new JSONObject((java.util.HashMap<String, Object>)obj);
			if(cache.getTrackById(tmp.getInt("id")) == null) {
				cache.addTrack(LyricUtil.retrieveTrackById(cache, tmp.getInt("id")));
			}
			tracks.add(cache.getTrackById(tmp.getInt("id")));
		}
		return tracks;
	}

	@Override
	public Artist getArtist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getReleaseYear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString() {
		return String.format("%s: %s (ID: %s)", this.getClass().getSimpleName(), this.getName(), this.getId());
	}
}
