package com.l2code.contato_service.utils;

import java.util.regex.Pattern;

public class ContatoUtils {

    public static boolean isValid(String str, Regex regex) {

        return Pattern.compile(regex.getRegex()).matcher(str).matches();
    }

    public enum Regex {
        EMAIL("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"),
        PHONE("^\\d{11}$");

        private final String regex;

        Regex(String regex) {
            this.regex = regex;
        }

        public String getRegex() {
            return regex;
        }
    }
}
