package com.epam.semeniuk.common.extractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Extractor<T> {

    T extractFromRequest(HttpServletRequest request) throws IOException, ServletException;
}
