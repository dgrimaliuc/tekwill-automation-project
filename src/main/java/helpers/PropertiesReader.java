package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertiesReader {

    public static String getProperty(String key) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/environment.properties"));
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
        String result = prop.getProperty(key);
        return replacePlaceholders(result);
    }


    private static String replacePlaceholders(String value) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)}");
        Matcher matcher = pattern.matcher(value);
        StringBuilder buffer = new StringBuilder();

        while (matcher.find()) {
            String placeholder = matcher.group(1);
            String replacement = System.getProperty(placeholder, "");
            matcher.appendReplacement(buffer, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    public static String getPropertyOrDefault(String key, String def) {
        return Optional.ofNullable(getProperty(key)).orElse(def);
    }

    public static void main(String[] args) {
        System.out.println(getProperty("driver-path"));
    }
}
