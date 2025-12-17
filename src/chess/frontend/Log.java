package chess.frontend;

import javax.swing.*;
import java.awt.*;
import java.io.*;


public class Log {
    private JTextArea moveLog;
    private JTextArea minimaxData;
    private int lineNumber = 1;

    public Log() {

    }

    public JPanel GeneratePanel() {

        JPanel container = new JPanel();
        container.setSize(200, 400);
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        //Title
        JLabel moveLogTitle = new JLabel("MOVE LOG", JLabel.CENTER);

        //Move Log
        moveLog = new JTextArea();
        moveLog.setEditable(false);
        //moveLog.setPreferredSize(new Dimension(200, 200));

        JScrollPane scrollPane = new JScrollPane(moveLog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        //Minimax Data
        minimaxData = new JTextArea();
        minimaxData.setEditable(false);
        minimaxData.setMaximumSize(new Dimension(200, 50));
        minimaxData.setText("""
                Search Depth:
                Best Move:
                Search Time:""");

        container.add(moveLogTitle);
        container.add(scrollPane);
        container.add(Box.createRigidArea(new Dimension(200, 20)));
        container.add(minimaxData);

        container.setBounds(500, 100, container.getWidth(), container.getHeight());

        return container;
    }

    public void UpdateMoveLog(String message) {
        moveLog.append(lineNumber + ". " + message + "\n");
        lineNumber++;
    }

    public void UpdateMinimaxLog(int depth, int best, float time) {
        minimaxData.setText(
                "Search Depth: " + depth + '\n' +
                        "Best Move: " + best + '\n' +
                        "Search Time: " + Math.round(time * Math.pow(10, 2)) / Math.pow(10, 2) + "s");
    }

}
