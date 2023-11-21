package mirela_b;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class BranchComparing {
    private String[] commands;

    public BranchComparing() {
    }

    public String[] defineCommands(String... command) {
        commands = Stream.concat(Stream.of(command), Stream.of()).toArray(String[]::new);
        return commands;
    }

    public String branchValidation(String branch) {
        if (branch.isEmpty() || branch.isBlank()) {
            branch = executeGitBranch();
        }
        return branch;
    }


    public String executeGitBranch() {
        String line = "";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(defineCommands("git", "branch", "--show-current"));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((line = reader.readLine()) != null) {
                return line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public void executeGitDiff(String branch) {
        String line;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(defineCommands("git", "diff", branchValidation(branch) + "..origin/master"));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}