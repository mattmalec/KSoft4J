package net.explodingbush.ksoftapi.enums;

public enum ImageTag {
    PEPE, DOGE, KAPPA, DAB, BIRB, DOG, FLOOFS, FBI, GLOMP, LICK, KISS, SMACK, SPANK, PAT, HUG, HENTAI_GIF, LEWD, NEKO, CODE;

	public boolean isNSFW() {
		return isNSFW(this);
	}
	public boolean isNSFW(ImageTag tag) {
		return tag.equals(HENTAI_GIF) || tag.equals(NEKO) || tag.equals(LICK) || tag.equals(LEWD);
	}
}
