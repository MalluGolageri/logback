package com.backlog.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;

@Plugin(name = "LogMaskingConverter", category = "Converter")
@ConverterKeys({"spi", "trscId"})
public class LogMaskingConverter extends ClassicConverter {
    private static final String CREDIT_CARD_REGEX = "([0-9]{16})";
    ;
    private static final Pattern CREDIT_CARD_PATTERN = Pattern.compile(CREDIT_CARD_REGEX);
    private static final String CAREDIT_CARD_REPLACEMENT_REGEX = "XXXXXXXXXXXXXXXX";

    private static final String CVV_REGEX = "([0-9]{3})";
    private static final Pattern CVV_PATTERN = Pattern.compile(CVV_REGEX);
    private static final String CVV_REPLACEMENT_REGEX = "***";

    private static final String SSN_REGEX = "([0-9]{9})";
    private static final Pattern SSN_PATTERN = Pattern.compile(SSN_REGEX);
    private static final String SSN_REPLACEMENT_REGEX = "*********";

    private String mask(String message) {
        Matcher matcher = null;
        StringBuffer buffer = new StringBuffer();

        matcher = CREDIT_CARD_PATTERN.matcher(message);
        maskMatcher(matcher, buffer, CAREDIT_CARD_REPLACEMENT_REGEX);
        message = buffer.toString();
        buffer.setLength(0);

        matcher = SSN_PATTERN.matcher(message);
        maskMatcher(matcher, buffer, SSN_REPLACEMENT_REGEX);
        message = buffer.toString();
        buffer.setLength(0);

        matcher = CVV_PATTERN.matcher(message);
        maskMatcher(matcher, buffer, CVV_REPLACEMENT_REGEX);

        return buffer.toString();
    }

    private StringBuffer maskMatcher(Matcher matcher, StringBuffer buffer, String maskStr) {
        while (matcher.find()) {
            matcher.appendReplacement(buffer, maskStr);
        }
        matcher.appendTail(buffer);
        return buffer;
    }

    public String convert(ILoggingEvent loggingEvent) {
        return mask(loggingEvent.getFormattedMessage());
    }
}

//Just changing commit message