package chess.frontend;

import chess.utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options implements ActionListener {
    JButton confirmButton;
    JCheckBox whiteToggle, blackToggle;
    JCheckBox alphaToggle;
    JTextField searchDepthField;
    GameSettings settings;

    public JPanel GeneratePanel() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));

        //White CPU TOGGLE
        whiteToggle = new JCheckBox();
        whiteToggle.setText("White CPU");

        //Black CPU TOGGLE
        blackToggle = new JCheckBox();
        blackToggle.setText("Black CPU");

        //Alpha-beta TOGGLE
        alphaToggle = new JCheckBox();
        alphaToggle.setText("Use Alpha-Beta");

        //Search Depth
        JLabel sdLabel = new JLabel("Search Depth");
        searchDepthField = new JTextField();
        searchDepthField.setText("4");


        //Confirm Button
        confirmButton = new JButton();
        confirmButton.setText("Confirm Changes");
        confirmButton.addActionListener(this);

        container.add(whiteToggle);
        container.add(blackToggle);
        container.add(alphaToggle);
        container.add(Box.createRigidArea(new Dimension(50, 20)));
        container.add(sdLabel);
        container.add(Box.createRigidArea(new Dimension(10, 20)));
        container.add(searchDepthField);
        container.add(Box.createRigidArea(new Dimension(50, 20)));
        container.add(confirmButton);

        return container;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == confirmButton) {
            GameSettings.isWhiteCPU = whiteToggle.isSelected();
            GameSettings.isBlackCPU = blackToggle.isSelected();
            GameSettings.doAlphaBeta = alphaToggle.isSelected();

            String text = searchDepthField.getText();
            if (text != null) {
                try {
                    GameSettings.searchDepth = Integer.parseInt(text);
                } catch (Exception ignored) {
                } finally {
                    searchDepthField.setText(String.valueOf(GameSettings.searchDepth));
                }
            }
        }
    }
}
