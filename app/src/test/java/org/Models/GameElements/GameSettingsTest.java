package org.Models.GameElements;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameSettingsTest {

    @Test
    public void testGetterSetter() {
        GameSettings settings = new GameSettings();
        assertFalse(settings.haveSetting("test"));
        assertEquals(settings.getSetting("test", "veloreTest2"), "veloreTest2");
        settings.setSetting("test", "veloreTest");
        assertTrue(settings.haveSetting("test"));
        assertEquals(settings.getSetting("test", "veloreTest2"), "veloreTest");
    }

    @Test
    public void testSpecialGetter(){
        GameSettings settings = new GameSettings();
        settings.setSetting("num", "-7");
        assertFalse(settings.getBoolSetting("bool", false));
        settings.setSetting("bool", "true");
        assertTrue(settings.getBoolSetting("bool", false));
        assertEquals(settings.getSettingNumber("num", 4), -7);
        assertEquals(settings.getSettingPositiveNumber("num", 4), 4);
    }
}
