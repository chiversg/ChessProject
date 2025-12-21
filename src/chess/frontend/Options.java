package chess.frontend;

import chess.utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options implements ActionListener {
    JButton confirmButton;
    JRadioButton whiteToggle;
    JRadioButton blackToggle;
    JRadioButton noneToggle;
    ButtonGroup cpuSelect;
    JCheckBox alphaToggle;
    JTextField searchDepthField;

    public JPanel GeneratePanel() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));

        //
        cpuSelect = new ButtonGroup();

        //White CPU TOGGLE
        noneToggle = new JRadioButton("No CPU", true);
        whiteToggle = new JRadioButton("White CPU", GameSettings.isWhiteCPU);
        blackToggle = new JRadioButton("Black CPU", GameSettings.isBlackCPU);

        cpuSelect.add(whiteToggle);
        cpuSelect.add(blackToggle);
        cpuSelect.add(noneToggle);

        //Alpha-beta TOGGLE
        alphaToggle = new JCheckBox("", GameSettings.doAlphaBeta);
        alphaToggle.setText("Use Alpha-Beta");

        //Search Depth
        JLabel sdLabel = new JLabel("Search Depth");
        searchDepthField = new JTextField();
        searchDepthField.setText(String.valueOf(GameSettings.searchDepth));


        //Confirm Button
        confirmButton = new JButton();
        confirmButton.setText("Confirm Changes");
        confirmButton.addActionListener(this);

        container.add(whiteToggle);
        container.add(blackToggle);
        container.add(noneToggle);
        container.add(Box.createRigidArea(new Dimension(30, 20)));
        container.add(alphaToggle);
        container.add(Box.createRigidArea(new Dimension(30, 20)));
        container.add(sdLabel);
        container.add(Box.createRigidArea(new Dimension(10, 20)));
        container.add(searchDepthField);
        container.add(Box.createRigidArea(new Dimension(30, 20)));
        container.add(confirmButton);

        return container;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (!noneToggle.isSelected()) {
            GameSettings.isWhiteCPU = whiteToggle.isSelected();
            GameSettings.isBlackCPU = blackToggle.isSelected();
        } else {
            GameSettings.isWhiteCPU = false;
            GameSettings.isBlackCPU = false;
        }

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
