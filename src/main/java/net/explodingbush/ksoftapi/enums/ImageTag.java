package net.explodingbush.ksoftapi.enums;

import java.util.Arrays;

public enum ImageTag {
	
    PEPE, 
    DOGE, 
    KAPPA,
    DAB, 
    BIRB, 
    DOG, 
    FBI, 
    LICK, 
    KISS, 
    SMACK, 
    SPANK, 
    PAT, 
    HUG, 
    HENTAI_GIF, 
    LEWD, 
    NEKO;
	
	public boolean isNSFW(ImageTag tag) {
		return Arrays.asList(NEKO, HENTAI_GIF, LEWD, LICK).contains(tag);
	}
	public boolean isNSFW() {
		return isNSFW(this);
	}
}
