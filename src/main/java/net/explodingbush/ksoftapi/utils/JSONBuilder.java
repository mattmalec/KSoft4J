package net.explodingbush.ksoftapi.utils;

import net.explodingbush.ksoftapi.enums.Routes;
import okhttp3.*;
import org.json.JSONArray;
//import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JSONBuilder {

    private OkHttpClient client = new OkHttpClient();
    private final String userAgent = "KSoft4J/1.0";


    public JSONObject request(String url) {
    	Checks.notNull(url, "url");
        try {
            String source = client.newCall(new Request.Builder().url(url).addHeader("User-Agent", userAgent).build()).execute().body().string();
            return new JSONObject(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String requestRaw(String url) {
    	Checks.notNull(url, "url");
        try {
            return client.newCall(new Request.Builder().url(url).addHeader("User-Agent", userAgent).build()).execute().body().string();
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
    	String source = null;
        try {
            source = client.newCall(new Request.Builder().addHeader("Authorization", "Bearer " + token)
                    .url(url).addHeader("User-Agent", userAgent).build()).execute().body().string();
            return new JSONObject(source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public JSONObject bulkBanCheck(JSONObject json, String token, Routes route) {
        Checks.notNull(json, "IDs");
        try {
            Response response = client.newCall(new Request.Builder().addHeader("Authorization", "Bearer " + token)
                    .url(route.toString()).addHeader("User-Agent", userAgent).addHeader("Content-Type", "application/json").post(RequestBody.create(MediaType.parse("application/json"), json.toString().getBytes())).build()).execute();
            String responseBody = response.body().string();
            JSONObject bulk = new JSONObject();
            if(!responseBody.startsWith("[")) {
                bulk = new JSONObject(responseBody);
            } else {
                bulk.put("data", new JSONArray(responseBody));
            }
            return bulk;
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
                    .url(url).addHeader("User-Agent", userAgent).post(body.build()).build()).execute().body().string();
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
            return client.newCall(new Request.Builder().addHeader("User-Agent", userAgent).addHeader("Authorization", "Bearer " + token)
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
