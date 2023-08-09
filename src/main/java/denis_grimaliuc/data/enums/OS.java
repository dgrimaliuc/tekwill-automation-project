package denis_grimaliuc.data.enums;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public enum OS {
    WINDOWS("windows"),
    MAC("mac");


    OS(String os) {
        this.os = os;
    }

    private final String os;

    public boolean isCurrentOs() {
        String value = getCurrentOS();
        return this.os.equals(value);
    }

    public static String getCurrentOS() {
        return System.getProperty("current_os", EMPTY);
    }
}
