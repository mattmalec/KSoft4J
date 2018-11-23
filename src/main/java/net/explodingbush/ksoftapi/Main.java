package net.explodingbush.ksoftapi;

import net.explodingbush.ksoftapi.entities.*;
import net.explodingbush.ksoftapi.hooks.KSoftListenerAdapter;

import java.util.List;

public class Main extends KSoftListenerAdapter {
    public static void main(String[] args) {
        KSoftAPI api = new KSoftAPI("93bd74e8484d50ad87b404f0632a6ee905ccc65a");
        api.getWebhookManager()
                .setPort(5000)
                .setToken("eP2orU0noGdcV0CmVJ8w0WnL5S73EYAu")
                .addEventListener(new Main());
//                .start();
        BulkBan bulkBan = api.getBan().checkBulkBan();
        bulkBan.addId("510113958464716800").addId("503590053453365259").addId("428422645663662080");
        List<Ban> list = bulkBan.set().execute().getBulkBanList();
        System.out.println(list.toString());
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