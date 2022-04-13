// package ru.innopolis.problem1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Problem1 {
    public static void main(String[] args) {
        CompositeLogger logger = new CompositeLogger();
        logger.registerLogger(new FileLogger("log.txt"));
        logger.registerLogger(new ConsoleLogger());
        logger.log("Hello, world!");
        logger.log("This is my log");
    }
}

interface Logger {
    public void log(String item);
}

class CompositeLogger implements Logger {
    private List<Logger> loggers;

    public CompositeLogger() {
        this.loggers = new ArrayList<>();
    }

    public void registerLogger(Logger logger) {
        this.loggers.add(logger);
    }

    @Override
    public void log(String item) {
        for (Logger logger : this.loggers) {
            logger.log(item);
        }
    }
}

class ConsoleLogger implements Logger {
    public ConsoleLogger() {
    }

    @Override
    public void log(String item) {
        System.out.println("Log: " + item);
    }
}

class FileLogger implements Logger {
    private String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void log(String item) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath, true))) {
            writer.write(item + "\n");
        } catch (IOException e) {
            // TODO: display error in some way?
        }
    }
}
