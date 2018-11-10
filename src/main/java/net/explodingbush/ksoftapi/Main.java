package net.explodingbush.ksoftapi;

import net.explodingbush.ksoftapi.entities.Lyric;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        KSoftAPI api = new KSoftAPI("27ad9320ead0dba5c43bf00856256f88bbe30024");
        List<Lyric> lyrics = api.getLyrics("dead dreams").execute();
        Lyric logic = lyrics.get(0);
        System.out.println(logic.getFullTitle());
    }
}
