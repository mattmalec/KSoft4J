package net.explodingbush.ksoftapi.utils;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import net.explodingbush.ksoftapi.enums.Routes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JSONBuilder {

    private OkHttpClient client = new OkHttpClient();


    public JSONObject request(String url) {
    	Checks.notNull(url, "url");
        try {
            String source = client.newCall(new Request.Builder().url(url).build()).execute().body().string();
            return new JSONObject(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String requestRaw(String url) {
    	Checks.notNull(url, "url");
        try {
            return client.newCall(new Request.Builder().url(url).build()).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JSONObject getJSONResponse(Response response) {
    	Checks.notNull(response, "response");
        try {
            return new JSONObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JSONObject requestKsoft(String url, String token) {
    	Checks.notNull(url, "url");
    	Checks.notNull(token, "token");
        try {
            String source = client.newCall(new Request.Builder().addHeader("Authorization", "Bearer " + token)
                    .url(url).build()).execute().body().string();
            return new JSONObject(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JSONObject addBanKsoft(JSONObject json, Routes route, String token) {
    	Checks.notNull(json, "json");
    	Checks.notNull(route, "route");
    	Checks.notNull(token, "token");
    	return addBanKsoft(json, route.toString(), token);
    }
    public JSONObject addBanKsoft(JSONObject json, String url, String token) {
    	Checks.notNull(url, "url");
    	Checks.notNull(json, "json");
    	Checks.notNull(token, "token");
        FormBody.Builder body = new FormBody.Builder();
        if(json.has("user")) {
            body.add("user", json.getString("user"));
        }
        if(json.has("mod")) {
            body.add("mod", json.getString("mod"));
        }
        if(json.has("user_name")) {
            body.add("user_name", json.getString("user_name"));
        }
        if(json.has("user_discriminator")) {
            body.add("user_discriminator", json.getString("user_discriminator"));
        }
        if(json.has("reason")) {
            body.add("reason", json.getString("reason"));
        }
        if(json.has("proof")) {
            body.add("proof", json.getString("proof"));
        }
        if(json.has("appeal_possible")) {
            body.add("appeal_possible", json.getString("appeal_possible"));
        }
        try {
            String source = client.newCall(new Request.Builder().addHeader("Authorization", "Bearer " + token)
                    .url(url).post(body.build()).build()).execute().body().string();
            return new JSONObject(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Response requestKsoftResponse(String url, String token) {
    	Checks.notNull(url, "url");
    	Checks.notNull(token, "token");
        try {
            return client.newCall(new Request.Builder().addHeader("Authorization", "Bearer " + token)
                    .url(url).build()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public File getImage(String title, String url) {
    	Checks.notNull(url, "url");
    	Checks.notNull(title, "title");
        try {
            URL imageURL = new URL(url);
            BufferedImage img = ImageIO.read(imageURL.openStream());
            File image = new File(title + ".png");
            ImageIO.write(img, "png", image);
            return image;
        } catch(IOException err) {
            err.printStackTrace();
        }
        return null;
    }
}
