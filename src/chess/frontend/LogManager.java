package chess.frontend;

import chess.utilities.ChessUtil;

import javax.swing.*;
import java.awt.*;


public class LogManager {
    private JTextArea moveLog;
    private JTextArea minimaxData;
    private JLabel turnLabel;
    private int lineNumber = 1;

    public JPanel GeneratePanel() {

        JPanel container = new JPanel();
        container.setSize(200, 400);
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        //Title
        JLabel moveLogTitle = new JLabel("MOVE LOG");
        moveLogTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Move Log
        moveLog = new JTextArea();
        moveLog.setEditable(false);
        //moveLog.setPreferredSize(new Dimension(200, 200));

        JScrollPane scrollPane = new JScrollPane(moveLog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(200, 200));

        //Current Move
        turnLabel = new JLabel("WHITE TO MOVE");
        turnLabel.setFont(new Font("San Serif", Font.PLAIN, 20));
        turnLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        container.add(turnLabel);
        container.add(minimaxData);

        container.setBounds(500, 100, container.getWidth(), container.getHeight());

        return container;
    }

    public void UpdateMoveLog(String message) {
        moveLog.append(lineNumber + ". " + message + "\n");
        lineNumber++;
    }

    public void UpdateMinimaxLog(int depth, float best, double time) {
        minimaxData.setText(
                "Search Depth: " + depth + '\n' +
                        "Best Move: " + best + '\n' +
                        "Search Time: " + Math.round(time * Math.pow(10, 2)) / Math.pow(10, 2) + "s");
    }
    public void UpdateTurnLabel(ChessUtil.Turn turn){
        switch(turn){
            case White:
                turnLabel.setText("WHITE TO MOVE");
                break;
            case Black:
                turnLabel.setText("BLACK TO MOVE");
                break;
            case CPU:
                turnLabel.setText("CPU IS THINKING");
                break;
        }
    }
}
