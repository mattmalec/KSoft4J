package net.explodingbush.ksoftapi.entities;

@Deprecated
public interface Lyric {

	//TODO: javadocs
	
	String getArtistName();
	String getSearchString();
	String getTitle();
	String getFullTitle();
	String getLyrics();
	String getAlbumArtUrl();
	String[] getAlbums();
	int getArtistId();
	int getSongId();
	int[] getAlbumIds();
	int[] getAlbumReleaseYears();
	
}
