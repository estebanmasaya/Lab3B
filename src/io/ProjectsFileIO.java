package io;

import model.Project;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Hints on how to implement serialization and deserialization
 * of lists of projects and users.
 */
public class ProjectsFileIO {

    /**
     * Call this method before the application exits, to store the users and projects,
     * in serialized form.
     */
    public static void serializeToFile(File file, List<Project> data) throws IOException {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fout = new FileOutputStream(file);
            oos = new ObjectOutputStream(fout);

            oos.writeObject(data);
        } finally {
            oos.close();
        }
    }

    /**
     * Call this method at startup of the application, to deserialize the users and
     * from file the specified file.
     */
    @SuppressWarnings("unchecked")
    public static List<Project> deSerializeFromFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        ArrayList<Project> list;
        try {
            FileInputStream fIn = new FileInputStream(file);
            ois = new ObjectInputStream(fIn);

            list = (ArrayList<Project>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw e;
        } finally {
            ois.close();
        }
        return list;
    }

    private ProjectsFileIO() {
    }
}