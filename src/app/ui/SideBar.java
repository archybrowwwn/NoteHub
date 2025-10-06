package app.ui;

import app.main.MainWindow;
import app.model.Note;
import app.model.NoteRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SideBar extends JPanel {
    private final JPanel container;
    private final List<Note> notes = new ArrayList<>();
    private final MainWindow parent;
    private final NoteRepository repo = new NoteRepository();

    public SideBar(MainWindow parent) {
        this.parent = parent;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(220, 0));

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        add(new JScrollPane(container,
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),
                BorderLayout.CENTER);

        for (String title : repo.listNoteTitles()) {
            try {
                Note note = repo.loadNote(title);
                notes.add(note);
                addNoteButton(note);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (notes.isEmpty()) {
            Note note = new Note("Untitled 1", "");
            notes.add(note);
            addNoteButton(note);
        }
    }

    public int getNextUntitledIndex() {
        int max = 0;
        for (Note note : notes) {
            if (note.getTitle().startsWith("Untitled")) {
                try {
                    int n = Integer.parseInt(note.getTitle().split(" ")[1]);
                    if (n > max) max = n;
                } catch (Exception ignored) {}
            }
        }
        return max + 1;
    }

    public void addNoteButton(Note note) {
        notes.add(note);

        JButton button = new JButton(note.getTitle());
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);

        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        button.setMinimumSize(new Dimension(100, 36));
        button.setPreferredSize(new Dimension(200, 36));

        button.addActionListener(e -> {
            parent.getEditor().setText(note.getText());
            parent.getMenu().setCurrentNote(note);
        });

        container.add(button);
        container.revalidate();
        container.repaint();
    }
}