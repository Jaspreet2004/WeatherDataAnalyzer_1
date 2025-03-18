# Weather Data Analyzer

## Overview
This Java application reads weather data from a CSV file and provides analytical features, leveraging modern Java features such as:
- **Records** for data representation
- **Enhanced switch statements** for temperature categorization
- **Lambdas & Streams** for efficient data processing
- **Text Blocks** for formatted output
- **Pattern Matching**

## Features
- Parse weather data (temperature, humidity, precipitation) from a CSV file
- Compute the **average temperature** for a specific **month**
- Identify **days** where the temperature exceeds a given **threshold**
- Count the **number of rainy days**
- Categorize weather conditions as **Hot, Warm, or Cold** using an enhanced switch

## Prerequisites
- Java 21 or later
- IntelliJ IDEA or any preferred IDE

## Example CSV Data
```
Date,Temperature,Humidity,Precipitation
2023-08-01,32.5,65,0.0
2023-08-02,35.0,60,0.2
```

## Example Output
```
Weather Data Analysis
---------------------
Average Temperature (August): 33.75
Hot Days (>30°C): [2023-08-01, 2023-08-02]
Rainy Days: 1
```
