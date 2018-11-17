package net.explodingbush.ksoftapi;

import net.explodingbush.ksoftapi.entities.BanEvent;
import net.explodingbush.ksoftapi.entities.UnbanEvent;
import net.explodingbush.ksoftapi.entities.VoteEvent;
import net.explodingbush.ksoftapi.hooks.KSoftListenerAdapter;

public class Main extends KSoftListenerAdapter {
    public static void main(String[] args) {
        KSoftAPI api = new KSoftAPI("bc39f48ed2c43b33da1ec6c880340d9425b6a81a");
        api.getWebhookManager()
                .setPort(5000)
                .setToken("j5MR43UNcxjw5Gs3PwPAyE9tyxPWvTy7")
                .addEventListener(new Main())
                .start();
//        api.getBan().getBanList(10).execute().getBannedList().forEach(ban -> System.out.println(ban.getId()));
    }

    @Override
    public void onBan(BanEvent event) {
        System.out.println("Dispatched the goober fucking BanEvent");
        System.out.println(event.getBan().getId());
    }

    @Override
    public void onUnban(UnbanEvent event) {
        System.out.println("Eliminated unBan cringer");
        System.out.println(event.getBan().getReason());
    }

    @Override
    public void onVote(VoteEvent event) {
        System.out.println("Vote bitch");
        System.out.println(event.getVote().getUserId());
    }
}