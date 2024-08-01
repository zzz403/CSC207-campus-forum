package com.imperial.academia.entity.chat_message;

import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDataTest {
    private MapData mapData;

    @BeforeEach
    public void init(){
        mapData = new MapData(1,1,"");
    }

    @Test
    void setLatitude() {
        mapData.setLatitude(20);
        assertEquals(20,mapData.getLatitude());
    }

    @Test
    void setLongitude() {
        mapData.setLongitude(30);
        assertEquals(30,mapData.getLongitude());
    }

    @Test
    void setLocationInfo() {
        mapData.setLocationInfo("info");
        assertEquals("info",mapData.getLocationInfo());
    }
}