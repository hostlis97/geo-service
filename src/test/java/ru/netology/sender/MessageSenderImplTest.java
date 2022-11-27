package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {
    private MessageSender messageSender;
    private GeoService geoService;
    private LocalizationService localizationService;
    private Location locationRus;
    private Location locationUsa;
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";
    private static final String IP_ADDRESS_HEADER = "x-real-ip";
    private Map<String, String> headers;

    @BeforeEach
    public void init() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
        locationRus = new Location("Moscow", Country.RUSSIA, null, 0);
        locationUsa = new Location("New York", Country.USA, null, 0);
        headers = new HashMap<String, String>();
    }

    @Test
    public void sendLocationRus() {
        Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(locationRus);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        headers.put(IP_ADDRESS_HEADER, MOSCOW_IP);

        String result = messageSender.send(headers);

        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    public void sendLocationUsa() {
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(locationUsa);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        headers.put(IP_ADDRESS_HEADER, NEW_YORK_IP);

        String result = messageSender.send(headers);

        Assertions.assertEquals("Welcome", result);
    }

}