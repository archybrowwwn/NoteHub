package app.ui;

import app.data.Note;
import app.data.NoteRepository;
import app.main.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SideBar extends JPanel {

    // ---------- Constants ----------
    private static final int SIDEBAR_WIDTH = 220;
    private static final int BUTTON_HEIGHT = 36;

    // ---------- Dependencies ----------
    private final MainWindow mainWindow;
    private final NoteRepository noteRepository = new NoteRepository();

    // ---------- UI Components ----------
    private final JPanel notesContainer;

    // ---------- Data ----------
    private final List<Note> notes = new ArrayList<>();

    public SideBar(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        configureLayout();
        notesContainer = createNotesContainer();
        add(createScrollPane(notesContainer), BorderLayout.CENTER);

        loadExistingNotes();
        createDefaultNote();
    }

    private void configureLayout() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(SIDEBAR_WIDTH, 0));
    }

    private JPanel createNotesContainer() {
        JPanel notesContainer = new JPanel();
        notesContainer.setLayout(new BoxLayout(notesContainer, BoxLayout.Y_AXIS));
        return notesContainer;
    }

    private JScrollPane createScrollPane(JPanel notesContainer) {
        return new JScrollPane(
                notesContainer,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
    }

    private void loadExistingNotes() {
        for (String title : noteRepository.listNoteTitles()) {
            try {
                Note note = noteRepository.loadNote(title);
                notes.add(note);
                addNoteButton(note);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createDefaultNote() {
        if (notes.isEmpty()) {
            Note note = new Note("Untitled 1", "");
            notes.add(note);
            createNoteButton(note);
        }
    }

    public int getNextUntitledIndex() {
        int maxIndex = 0;
        for (Note note : notes) {
            if (note.getTitle().startsWith("Untitled")) {
                try {
                    int n = Integer.parseInt(note.getTitle().split(" ")[1]);
                    if (n > maxIndex) maxIndex = n;
                } catch (Exception ignored) {}
            }
        }
        return maxIndex + 1;
    }

    public void addNoteButton(Note note) {
        notes.add(note);
        createNoteButton(note);
    }

    private void createNoteButton(Note note) {
        JButton button = new JButton(note.getTitle());
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(SIDEBAR_WIDTH, BUTTON_HEIGHT));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, BUTTON_HEIGHT));
        button.setMinimumSize(new Dimension(100, BUTTON_HEIGHT));

        button.addActionListener(e -> openNote(note));

        notesContainer.add(button);
        notesContainer.revalidate();
        notesContainer.repaint();
    }

    private void openNote(Note note) {
        mainWindow.getViewController().getEditor().setText(note.getText());
        mainWindow.getMenu().setCurrentNote(note);
    }
}