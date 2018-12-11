package ru.javawebinar.topjava.util;

import org.springframework.validation.BindingResult;

import java.util.StringJoiner;

public class BindingResultUtil {
    private BindingResultUtil(){}
    public static StringJoiner getStringJoiner(BindingResult result) {
        StringJoiner joiner = new StringJoiner("<br>");
        result.getFieldErrors().forEach(
                fe -> {
                    String msg = fe.getDefaultMessage();
                    if (msg != null) {
                        if (!msg.startsWith(fe.getField())) {
                            msg = fe.getField() + ' ' + msg;
                        }
                        joiner.add(msg);
                    }
                });
        return joiner;
    }
}
