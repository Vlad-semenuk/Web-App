package com.epam.semeniuk.locale.storage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public interface LocaleStorage {

   Locale getLocale(HttpServletRequest request);

   void saveLocale(HttpServletRequest request, HttpServletResponse response, Locale locale);
}
