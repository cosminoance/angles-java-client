package com.github.angleshq.angles.util;

import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class AnglesUtils {
    public static String getAnglesPropertyFromSystem(String property) throws AnglesPropertyNotGivenException {
        String propertyValue = System.getProperty(property);
        if(isBlank(propertyValue)) {
            throw new AnglesPropertyNotGivenException("Detected that property [" + property + "] was not given. Please add this mandatory property as a system property");
        }
        return propertyValue;
    }
}
