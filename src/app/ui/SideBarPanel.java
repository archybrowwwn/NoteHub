package app.ui;

import app.main.MainWindow;
import app.model.NoteFile;
import app.model.NoteRepository;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SideBarPanel extends JPanel {
    private JPanel tabsContainer;
    private List<NoteFile> notes = new ArrayList<>();
    private MainWindow parent;
    private NoteRepository repo = new NoteRepository();

    public SideBarPanel(MainWindow parent) {
        this.parent = parent;
        setLayout(new BorderLayout());

        tabsContainer = new JPanel();
        tabsContainer.setLayout(new BoxLayout(tabsContainer, BoxLayout.Y_AXIS));
        add(new JScrollPane(tabsContainer), BorderLayout.CENTER);

        for (String title : repo.listNoteTitles()) {
            try {
                NoteFile note = repo.loadNote(title);
                notes.add(note);
                addTabButton(note);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (notes.isEmpty()) {
            NoteFile firstNote = new NoteFile("Untitled 1", "");
            addTabButton(firstNote);
        }
    }

    public int getNextUntitledIndex() {
        int max = 0;
        for (NoteFile note : notes) {
            if (note.getTitle().startsWith("Untitled")) {
                try {
                    int n = Integer.parseInt(note.getTitle().split(" ")[1]);
                    if (n > max) max = n;
                } catch (Exception ignored) {}
            }
        }
        return max + 1;
    }

    public void addTabButton(NoteFile note) {
        NoteFile noteFinal = note;
        JButton tabButton = new JButton(noteFinal.getTitle());
        tabButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        tabButton.addActionListener(e -> {
            parent.getEditor().setText(noteFinal.getContent());
            parent.getMenu().setCurrentNote(noteFinal);
        });

        tabsContainer.add(tabButton);
        notes.add(noteFinal);
    }
}