package net.explodingbush.ksoftapi.entities;

public interface Lyric {

	//TODO: javadocs
	
	String getArtistName();
	String getSearchString();
	String getTitle();
	String getFullTitle();
	String getLyrics();
	
	
	String[] getAlbums();
	
	
	int getArtistId();
	int getSongId();
	
	int[] getAlbumIds();
	int[] getAlbumReleaseYears();
	
}
