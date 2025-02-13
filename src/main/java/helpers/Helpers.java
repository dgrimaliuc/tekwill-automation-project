package helpers;

import com.google.gson.Gson;
import io.cucumber.java.Scenario;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.reducers.ReducingMethod;
import org.apache.log4j.Logger;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Helpers {
    public static ArrayList<Object> stepResults = null;
    public static Logger log = createLogger(Helpers.class);

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(Helpers::generateCucumberReport));
    }


    public static <T> Logger createLogger(Class<T> clazz) {
        return Logger.getLogger(clazz);
    }

    public static <T> T getValue(String format, Class<T> resultType) {
        Gson gson = new Gson();
        var arg = gson.toJson(stepResults)
                .replaceAll("true", "True")
                .replaceAll("false", "False");

        PythonExecutor pe = new PythonExecutor(gson.toJson(format) + ".format(*" + arg + ")");
        pe.execute();
        return gson.fromJson("\"" + pe.getResult() + "\"", resultType);
    }

    public static void generateCucumberReport() {
        if (Files.exists(Path.of("target/cucumber-html-reports/cucumber.json"))) {
            File reportOutputDirectory = new File("target/");
            List<String> jsonFiles = new ArrayList<>();
            jsonFiles.add("target/cucumber-html-reports/cucumber.json");
            String buildNumber = "v1";
            String projectName = "Tekwill Automation";

            Configuration configuration = new Configuration(reportOutputDirectory, projectName);
            configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
            configuration.setBuildNumber(buildNumber);
            configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
            configuration.addReducingMethod(ReducingMethod.HIDE_EMPTY_HOOKS);
            // additional metadata presented on main page
            configuration.addClassifications("Platform", "Windows");
            configuration.addClassifications("Browser", "Chrome");
            configuration.addClassifications("Branch", "release/1.0");

            // optionally add metadata presented on main page via properties file
            List<String> classificationFiles = new ArrayList<>();
            configuration.addClassificationFiles(classificationFiles);
            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            reportBuilder.generateReports();
        } else
            System.out.println("Cucumber Json file was not found");
    }


    public static String[][] getExcelData(String fileName, String sheetName) {

        String[][] data;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheet(sheetName);
            XSSFRow row = sh.getRow(0);
            int noOfRows = sh.getPhysicalNumberOfRows(); // May not work if you create an empty cell
            int noOfCols = row.getLastCellNum();
            DataFormatter formatter = new DataFormatter();
            data = new String[noOfRows - 1][noOfCols];
            for (int rowNum = 1; rowNum < noOfRows; rowNum++) {
                for (int cellNum = 0; cellNum < noOfCols; cellNum++) {
                    row = sh.getRow(rowNum);
                    data[rowNum - 1][cellNum] = formatter.formatCellValue(row.getCell(cellNum));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return data;
    }

    public static String addQuotes(String value) {
        return "\"" + value + "\"";
    }


    public static void waitInSeconds(int seconds) {
        try {
            log.info("Wait for " + seconds + " seconds");
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Appender extends WriterAppender {
        private final Scenario scenario;

        public Appender(Scenario scenario) {
            this.scenario = scenario;
        }

        @Override
        public void doAppend(LoggingEvent event) {
            scenario.log(event.getRenderedMessage());
        }
    }
}
