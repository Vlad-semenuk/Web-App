package com.epam.semeniuk.locale;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

public class ApplicationSupportedLocale {

    private List<String> supportedLocales;
    private String defaultLocale;

    public ApplicationSupportedLocale(List<String> supportedLocales, String defaultLocale){
        this.supportedLocales = supportedLocales;
        this.defaultLocale = defaultLocale;
    }

    public  Locale getLocale(String languageName){
        if (supportedLocales.contains(languageName)){
            return new Locale(languageName);
        }
        return null;
    }

    public Locale searchPriorityLocale (Enumeration<Locale> locales){
        while (locales.hasMoreElements()){
            Locale locale = locales.nextElement();
            if (supportedLocales.contains(locale.getLanguage())){
                return locale;
            }
        }
        return new Locale(defaultLocale);
    }

    public List<Locale> replaceGetLocales(){
        List<Locale> locales = new ArrayList<>();
        for (String lang : supportedLocales){
            locales.add(new Locale(lang));
        }
        return locales;
    }
}
