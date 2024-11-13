package example.data.enums;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public enum OS {
    WINDOWS("windows"),
    MAC("mac");


    private final String os;

    OS(String os) {
        this.os = os;
    }

    public static String getCurrentOS() {
        return System.getProperty("current_os", EMPTY);
    }

    public boolean isCurrentOs() {
        String value = getCurrentOS();
        return this.os.equals(value);
    }
}
