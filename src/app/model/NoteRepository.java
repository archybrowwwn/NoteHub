package app.model;

import java.nio.file.*;
import java.util.*;

public class NoteRepository {
    private final String saveDir = "files";

    public NoteRepository() {
        new java.io.File(saveDir).mkdirs();
    }

    public void saveNote(NoteFile note) throws Exception {
        Files.write(Paths.get(saveDir, note.getTitle() + ".txt"), note.getContent().getBytes());
    }

    public NoteFile loadNote(String title) throws Exception {
        return new NoteFile(title, Files.readString(Paths.get(saveDir, title + ".txt")));
    }

    public List<String> listNoteTitles() {
        java.io.File folder = new java.io.File(saveDir);
        if (!folder.isDirectory()) return Collections.emptyList();

        List<String> titles = new ArrayList<>();
        for (String f : folder.list((d, name) -> name.endsWith(".txt"))) {
            titles.add(f.replaceFirst("\\.txt$", ""));
        }
        return titles;
    }
}