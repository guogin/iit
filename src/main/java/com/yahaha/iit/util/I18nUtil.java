package com.yahaha.iit.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18nUtil {
    public static String getMessage(String key, Locale locale) {
        if (locale == null) {
            locale = Locale.SIMPLIFIED_CHINESE;
        }
        ResourceBundle bundle = ResourceBundle.getBundle("i18n." + locale.toLanguageTag(), locale);
        return bundle.getString(key);
    }
}
