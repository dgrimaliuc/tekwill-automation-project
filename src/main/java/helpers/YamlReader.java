package helpers;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class YamlReader {
    String pathToFile;

    public YamlReader(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public YamlReader() {
        this("src/main/resources/chromeSettings/chrome.yaml");
    }

    public Map<String, Object> read() {
        Yaml yaml = new Yaml();
        try (FileInputStream in = new FileInputStream(pathToFile)) {
            return yaml.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
