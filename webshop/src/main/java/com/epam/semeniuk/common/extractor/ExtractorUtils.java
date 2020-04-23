package com.epam.semeniuk.common.extractor;


import org.apache.log4j.Logger;

public class ExtractorUtils {
    private static final Logger LOG = Logger.getLogger(ExtractorUtils.class);
    private ExtractorUtils(){

    }
    public static Integer checkInteger(String value) {
        try {
            int parsedValue = Integer.parseInt(value);
            if (parsedValue >= 0) {
                return parsedValue;
            }
        } catch (NumberFormatException e) {
            LOG.debug("Wrong number format of input parameter.");
        }
        return null;
    }
}
