package ru.bicev;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class FileIOHandler {

    public static <T extends Serializable> void saveToFile(T object, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(object);
        }
    }

    public static <T extends Serializable> T readFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (T) ois.readObject();
        }
    }

    public static <T extends Serializable> void saveListToFile(List<T> list, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        }
    }

    public static <T extends Serializable> List<T> readListFromFile(String fileName)
            throws ClassNotFoundException, IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        }
    }

}
