package com.imperial.academia.service;

import java.io.IOException;

public interface MapService {
    /**
     * Generates a map image for the specified group.
     *
     * @param groupId the ID of the group to generate the map for
     */
    void generateMapImage(int groupId, double latitude, double longitude);

    /**
     * Retrieves the location information for the map.
     *
     * @return the location information
     */
    String getLocationInfo(double latitude, double longitude) throws IOException;

    /**
     * Retrieves the user's location.
     *
     * @return the user's location latitude and longitude
     */
    double[] getUserLocation();

    /**
     * Retrieves the file path of the generated map image.
     *
     * @return the file path of the generated map image
     */
    String getFilePath();

}
