package ru.bicev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> void saveToFile(T object, String fileName) throws IOException {
        String json = objectMapper.writeValueAsString(object);

        try (FileOutputStream fos = new FileOutputStream(fileName);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter writer = new BufferedWriter(osw)) {
            ;
            writer.write(json);
        }
    }

    public static <T> T readFromFile(String fileName, Class<T> className) throws IOException {
        StringBuilder jsonBuilder = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(fileName);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }
        return objectMapper.readValue(jsonBuilder.toString(), className);
    }

    public static <T> void saveListToFile(List<T> list, String filename) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter writer = new BufferedWriter(osw)) {
            String json = objectMapper.writeValueAsString(list);
            writer.write(json);
        }
    }

    public static <T> List<T> readListFromFile(String filename, TypeReference<List<T>> typeReference)
            throws IOException {
        StringBuilder json = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(filename);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        return objectMapper.readValue(json.toString(), typeReference);
    }

}
