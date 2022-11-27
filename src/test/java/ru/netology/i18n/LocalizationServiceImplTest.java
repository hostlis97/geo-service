package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {
    private LocalizationService localizationService = new LocalizationServiceImpl();

    @Test
    public void localeRus() {
        String result = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals("Добро пожаловать", result);
    }

    @Test
    public void localeUsa() {
        String result = localizationService.locale(Country.USA);

        Assertions.assertEquals("Welcome", result);
    }
}