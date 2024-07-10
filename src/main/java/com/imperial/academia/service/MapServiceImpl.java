package com.imperial.academia.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Objects;
import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;

import com.imperial.academia.config.ApiKeyConfig;

public class MapServiceImpl implements MapService {
    private final String API_KEY;
    private String filePath;

    public MapServiceImpl() {
        if (Objects.equals(ApiKeyConfig.getMapBoxApiKey(), "")) {
            throw new IllegalArgumentException("API key not found.");
        }else{
            API_KEY = ApiKeyConfig.getMapBoxApiKey();
        }
        filePath = "";
    }

    @Override
    public void generateMapImage(int groupId, double latitude, double longitude){
        try {
            // 获取用户位置
//            double[] location = getUserLocation();
            int zoom = 16; // 增加缩放级别以放大图片
            int width = 800; // 图片宽度
            int height = 600; // 图片高度
            // 调整地图中心，使标记点不在中心位置
            double centerLatitude = latitude + (Math.random() - 0.5) * 0.01;
            double centerLongitude = longitude + (Math.random() - 0.5) * 0.01;

            // 获取地图瓦片
            String url = String.format(
                    "https://api.mapbox.com/styles/v1/mapbox/dark-v11/static/pin-l-embassy+f74e4e(%f,%f)/%f,%f,%d/%dx%d?access_token=%s",
                    longitude, latitude, centerLongitude, centerLatitude, zoom, width, height, API_KEY
            );
            InputStream input = new URL(url).openStream();
            BufferedImage mapImage = ImageIO.read(input);

            // 处理图片（裁剪和缩放）
            mapImage = processImage(mapImage);

            saveImage(mapImage, String.valueOf(groupId), longitude, latitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage processImage(BufferedImage image) {
        int cropHeight = image.getHeight() - 50;
        BufferedImage croppedImage = image.getSubimage(0, 0, image.getWidth(), cropHeight);

        // 放大图片
        int newWidth = (int) (croppedImage.getWidth() * 1.2); // 放大20%
        int newHeight = (int) (croppedImage.getHeight() * 1.2);
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(croppedImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return scaledImage;
    }

    private void saveImage(BufferedImage image, String groupId, double longitude, double latitude) {
        try {
            // 创建保存路径
            String dirPath = "resources/map/" + groupId;
            Files.createDirectories(Paths.get(dirPath));

            // 生成文件名
            int userId = 1; // 替换为实际的用户ID
            filePath = String.format("%s/%s_%f_%f_%d.png", dirPath, userId, longitude, latitude, Instant.now().getEpochSecond());
            System.out.println("File path: " + filePath);
            File outputfile = new File(filePath);

            // 保存图片
            ImageIO.write(image, "png", outputfile);
            System.out.println("Image saved as " + filePath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public double[] getUserLocation() {
        double[] location = new double[2];
        try {
            String ipApiUrl = "http://ip-api.com/json/";
            HttpURLConnection connection = (HttpURLConnection) new URL(ipApiUrl).openConnection();
            connection.setRequestMethod("GET");
            InputStream responseStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(responseStream);
            StringBuilder responseBuilder = new StringBuilder();
            int data = reader.read();
            while (data != -1) {
                responseBuilder.append((char) data);
                data = reader.read();
            }
            reader.close();
            JSONObject responseJson = new JSONObject(responseBuilder.toString());
            location[0] = responseJson.getDouble("lat");
            location[1] = responseJson.getDouble("lon");
        } catch (Exception e) {
            e.printStackTrace();
            location[0] = 37.7749;
            location[1] = -122.4194;
        }
        System.out.println("User location: " + location[0] + ", " + location[1]);
        return location;
    }

    @Override
    public String getLocationInfo(double latitude, double longitude) throws IOException {
//        double[] location = getUserLocation();
        OkHttpClient client = new OkHttpClient();


        String url = String.format(
                "https://api.mapbox.com/geocoding/v5/mapbox.places/%f,%f.json?access_token=%s",
                longitude, latitude, API_KEY
        );

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            JSONObject jsonResponse = new JSONObject(response.body().string());
            JSONArray features = jsonResponse.getJSONArray("features");
            if (!features.isEmpty()) {
                JSONObject place = features.getJSONObject(0);
                System.out.println("Location: " + place.getString("place_name"));
                return place.getString("place_name");
            } else {
                return "No location information found.";
            }
        } catch (Exception e) {
            return "An error occurred while retrieving the location.";
        }
    }

    @Override
    public String getFilePath() {
        return filePath;
    }
}
