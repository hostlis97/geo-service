package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {
    private GeoService geoService;
    private Location locationRus;
    private Location locationUsa;
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";

    @BeforeEach
    public void init() {
        geoService = Mockito.mock(GeoService.class);
        locationRus = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        locationUsa = new Location("New York", Country.USA, null,  0);
    }

    @Test
    public void moscowByIp() {
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(locationRus);
        Location result = geoService.byIp(MOSCOW_IP);

        Assertions.assertEquals(locationRus, result);
    }
    @Test
    public void new_YorkByIp() {
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(locationUsa);
        Location result = geoService.byIp(NEW_YORK_IP);

        Assertions.assertEquals(locationUsa, result);
    }
}