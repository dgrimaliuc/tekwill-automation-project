package helpers;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Requires to be installed Python
 * Requires to have PATH environment variable with python folder inside
 */
public class PythonExecutor {
    private String[] commands;
    private Object result;


    PythonExecutor(String pythonScript) {
        buildCommand(pythonScript);

    }

    public static <T> T executePythonScript(String script, Class<T> clazz) {
        Gson gson = new Gson();
        PythonExecutor pe = new PythonExecutor(script);
        pe.execute();
        return gson.fromJson(String.valueOf(pe.getResult()), clazz);
    }

    public static String executePythonScript(String script) {
        PythonExecutor pe = new PythonExecutor(script);
        pe.execute();
        return (String) pe.getResult();
    }

    private void buildCommand(String pythonScript) {
        // Needs to add in commands "python -c"
        // -c -   Carries out the command specified by string and then terminates
        this.commands = Stream.concat(Stream.of("python", "-c"),
                Stream.of("\"print(" + pythonScript.replaceAll("\"", "'") + ")\"")).toArray(String[]::new);
    }

    public void execute() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(commands);
        try {
            Process process = processBuilder.start();
            throwIfFailed(process);
            this.result = getOutput(process);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getResult() {
        return result;
    }

    private String getOutput(Process process) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        String result = null;
        String line;
        while ((line = reader.readLine()) != null) {
            result = line;
        }
        return result;
    }

    private void throwIfFailed(Process process) throws IOException {
        BufferedReader errorReader =
                new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errorLine;
        while ((errorLine = errorReader.readLine()) != null) {
            errorLine = new String(errorLine.getBytes(UTF_8));
            if (errorLine.contains("Error"))
                throw new AssertionError(errorLine);
        }

    }
}
