package net.explodingbush.ksoftapi.entities;

public interface Lyric {

	//TODO: javadocs
	
	String getArtistName();
	String getFullTitle();
	String getTitle();
	String getLyrics();
	
	
	String[] getAlbums();
	
	
	int getArtistId();
	int getSongId();
	
	int[] getAlbumIds();
	int[] getAlbumReleaseYears();
	
}
