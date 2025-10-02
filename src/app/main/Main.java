package app.main;

import app.history_saver.*;
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });

        LinkedList<Integer> List = new LinkedList<Integer>();
        for(int i = 0; i < 10; i++){
            List.add(i);
        }
        List.pop();
        for(int i = 0; i < 10; i++){
            System.out.println(List.get(i).data);
        }
    }
}