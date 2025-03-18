package org.example;
/**
 * # Weather Data Analyzer
 *
 * This Java application analyzes weather data from a CSV file.
 * It demonstrates modern Java features such as records, enhanced switch statements, text blocks, lambdas, and pattern matching.
 *
 * ## Features
 * - Parse weather data (temperature, humidity, precipitation) from a CSV file.
 * - Compute the average temperature for a given month.
 * - Identify days with temperatures above a threshold.
 * - Count rainy days.
 * - Categorize weather using enhanced switch statements.
 *
 * ## Example Usage
 * ```java
 * WeatherDataAnalyzer.run("weatherdata.csv");
 * ```
 */

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.*;

/** Record representing a weather data entry. */
record WeatherRecord(String date, double temperature, int humidity, double precipitation) {}

/** Interface defining weather analysis methods. */
interface WeatherDataAnalyzer {

    /** Reads weather data from a CSV file. */
    static List<WeatherRecord> readWeatherData(String fileName) throws IOException {
        InputStream inputStream = WeatherDataAnalyzer.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) throw new FileNotFoundException("File not found: " + fileName);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines()
                    .skip(1) // Skip header
                    .map(line -> line.split(","))
                    .map(parts -> new WeatherRecord(parts[0], Double.parseDouble(parts[1]),
                            Integer.parseInt(parts[2]), Double.parseDouble(parts[3])))
                    .collect(Collectors.toList());
        }
    }

    /** Calculates the average temperature for a specific month. */
    static double averageTemperature(List<WeatherRecord> data, String month) {
        return data.stream()
                .filter(record -> record.date().startsWith(month))
                .mapToDouble(WeatherRecord::temperature)
                .average()
                .orElse(Double.NaN);
    }

    /** Finds days with temperatures exceeding the given threshold. */
    static List<String> hotDays(List<WeatherRecord> data, double threshold) {
        return data.stream()
                .filter(record -> record.temperature() > threshold)
                .map(WeatherRecord::date)
                .toList();
    }

    /** Counts the number of rainy days. */
    static long countRainyDays(List<WeatherRecord> data) {
        return data.stream().filter(record -> record.precipitation() > 0).count();
    }

    /** Categorizes temperature levels. */
    static String categorizeTemperature(double temperature) {
        return switch ((int) temperature / 10) {
            case 3, 4 -> "Hot";
            case 2 -> "Warm";
            default -> "Cold";
        };
    }

    /** Runs the weather data analysis and prints results. */
    static void run(String fileName) throws IOException {
        List<WeatherRecord> data = readWeatherData(fileName);

        System.out.println("""
                Weather Data Analysis
                ---------------------
                """.stripIndent());

        System.out.println("Average Temperature (August): " + averageTemperature(data, "2023-08"));
        System.out.println("Hot Days (>30°C): " + hotDays(data, 30.0));
        System.out.println("Rainy Days: " + countRainyDays(data));
    }

    public static void main(String[] args) throws IOException {
        run("weatherdata.csv");
    }
}
