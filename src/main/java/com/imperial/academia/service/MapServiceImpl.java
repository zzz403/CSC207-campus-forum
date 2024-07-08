package com.imperial.academia.service;

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
import javax.imageio.ImageIO;
import org.json.JSONObject;

public class MapServiceImpl implements MapService {
    private String apiKey;

    public MapServiceImpl() {

    }

    public void generateMapImage(String groupId){
        try {
            // 获取用户位置
            double[] location = getUserLocation();
            double latitude = location[0];
            double longitude = location[1];
            int zoom = 14; // 增加缩放级别以放大图片
            int width = 800; // 图片宽度
            int height = 600; // 图片高度
            String apiKey = "pk.eyJ1IjoiYXVndXN0LXRlc3RpbmciLCJhIjoiY2x5YXozNjY4MTdkNTJrb20zZHIzZXNoMyJ9.kqlhF0lk9MiaYqa9acJG1A";

            // 调整地图中心，使标记点不在中心位置
            double centerLatitude = latitude + (Math.random() - 0.5) * 0.01;
            double centerLongitude = longitude + (Math.random() - 0.5) * 0.01;

            // 获取地图瓦片
            String url = String.format(
                    "https://api.mapbox.com/styles/v1/mapbox/dark-v11/static/pin-l-embassy+f74e4e(%f,%f)/%f,%f,%d/%dx%d?access_token=%s",
                    longitude, latitude, centerLongitude, centerLatitude, zoom, width, height, apiKey
            );
            InputStream input = new URL(url).openStream();
            BufferedImage mapImage = ImageIO.read(input);

            // 处理图片（裁剪和缩放）
            mapImage = processImage(mapImage);

            saveImage(mapImage, groupId, longitude, latitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage processImage(BufferedImage image) {
        // 裁剪图片底部的水印部分
        int cropHeight = image.getHeight() - 50; // 裁掉50像素
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
            String fileName = String.format("%s/%s_%f_%f_%d.png", dirPath, userId, longitude, latitude, Instant.now().getEpochSecond());
            File outputfile = new File(fileName);

            // 保存图片
            ImageIO.write(image, "png", outputfile);
            System.out.println("Image saved as " + fileName);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private double[] getUserLocation() {
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

    public static void main(String[] args) {
        new MapServiceImpl();
    }
}
