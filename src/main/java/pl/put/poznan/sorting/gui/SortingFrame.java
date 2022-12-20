package pl.put.poznan.sorting.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.*;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.logic.SortingAlgorithmProvider;
import pl.put.poznan.sorting.logic.SortingHandler;

public class SortingFrame extends JFrame {

    private Map<String, JCheckBox> algorithmCheckboxMap = new LinkedHashMap<>();
    private JComboBox<String> elementTypeComboBox;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JLabel statusLabel;
    private DefaultTableModel timeTableModel = new DefaultTableModel();

    public SortingFrame() {
        setTitle("Sorting Madness");
        setSize(470, 340);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        installComponents();
        setVisible(true);
    }

    private void installComponents() {
        setLayout(new MigLayout());
        add(createSettingsPanel(), "top, push");
        add(createInputPanel(), "top");
        add(createOutputPanel(), "top, wrap");
        add(createButtonAndStatusPanel(), "top, span");
    }

    private JPanel createSettingsPanel() {
        var panel = new JPanel(new MigLayout("wrap"));

        // Add elements type selection
        panel.add(new JLabel("Type:"));
        elementTypeComboBox = new JComboBox<>(new String[] {"Integers", "Strings"});
        panel.add(elementTypeComboBox);

        // Add algorithm selection
        panel.add(new JLabel("Algorithms:"));
        SortingAlgorithmProvider
                .getRegisteredAlgorithms()
                .forEach(algorithm -> {
                    var checkbox = new JCheckBox(algorithm.getDisplayName(), false);
                    algorithmCheckboxMap.put(algorithm.getId(), checkbox);
                    panel.add(checkbox);
                });

        return panel;
    }

    private JPanel createInputPanel() {
        var panel = new JPanel(new MigLayout("wrap"));

        panel.add(new JLabel("Input:"));
        inputTextArea = new JTextArea(13, 10);
        var scrollPane = new JScrollPane(inputTextArea);
        panel.add(scrollPane);

        return panel;
    }

    private JPanel createOutputPanel() {
        var panel = new JPanel(new MigLayout("wrap"));

        panel.add(new JLabel("Output:"));
        outputTextArea = new JTextArea(13, 10);
        outputTextArea.setEditable(false);
        var scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane);

        return panel;
    }

    private JPanel createButtonAndStatusPanel() {
        var panel = new JPanel(new MigLayout());

        // Add sort button
        var sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSortButtonClick();
            }
        });
        panel.add(sortButton);

        // Add status label
        statusLabel = new JLabel();
        panel.add(statusLabel);

        return panel;
    }

    private void onSortButtonClick() {
        // Reset output
        outputTextArea.setText(null);
        statusLabel.setText(null);
        algorithmCheckboxMap.forEach((algorithmId, checkbox) -> {
            var algorithm = SortingAlgorithmProvider.getAlgorithm(algorithmId);
            checkbox.setText(algorithm.getDisplayName());
        });

        // Prepare elements
        var rawElements = new LinkedList<>(Arrays.asList(inputTextArea.getText().split("\r?\n")));
        rawElements.removeIf(String::isBlank);

        if (rawElements.isEmpty()) {
            statusLabel.setText("Error: Nothing to sort.");
            return;
        }

        // Check : No algorithm selected
        boolean isAnyAlgorithmSelected = algorithmCheckboxMap
                .values().stream()
                .anyMatch(AbstractButton::isSelected);
        if (!isAnyAlgorithmSelected) {
            statusLabel.setText("Error: No algorithm selected.");
            return;
        }

        // Process
        switch (elementTypeComboBox.getSelectedIndex()) {
            // Integers selected
            case -1, 0 -> {
                sortIntegers(rawElements);
            }
            // Strings selected
            case 1 -> {
                sortStrings(rawElements);
            }
        }
    }

    private void sortIntegers(List<String> rawElements) {
        // Convert to integers & collect to array
        int i = 0;
        int[] elements = new int[rawElements.size()];
        for (var rawElement : rawElements) {
            try {
                elements[i++] = Integer.parseInt(rawElement);
            } catch (NumberFormatException ex) {
                statusLabel.setText("Error: Invalid elements.");
                return;
            }
        }

        // Sort
        var handler = new SortingHandler();
        int[] output = null;
        for (var entry : algorithmCheckboxMap.entrySet()) {
            var algorithmId = entry.getKey();
            var checkbox = entry.getValue();

            if (checkbox.isSelected()) {
                var elementsToSort = Arrays.copyOf(elements, elements.length);

                var algorithm = SortingAlgorithmProvider.getAlgorithm(algorithmId);
                handler.setAlgorithm(algorithm);
                long time = handler.sort(elementsToSort);
                if (output == null)
                    output = elementsToSort;

                checkbox.setText(String.format("%s (%dms)", checkbox.getText(), time));
            }
        }

        var outputJoiner = new StringJoiner("\n");
        for (var element : output)
            outputJoiner.add(String.valueOf(element));
        outputTextArea.setText(outputJoiner.toString());

        statusLabel.setText("Success!");
    }

    private void sortStrings(List<String> rawElements) {
        // Collect to array
        var elements = rawElements.toArray(new String[0]);

        // Sort
        var handler = new SortingHandler();
        String[] output = null;
        for (var entry : algorithmCheckboxMap.entrySet()) {
            var algorithmId = entry.getKey();
            var checkbox = entry.getValue();

            if (checkbox.isSelected()) {
                var elementsToSort = Arrays.copyOf(elements, elements.length);

                var algorithm = SortingAlgorithmProvider.getAlgorithm(algorithmId);
                handler.setAlgorithm(algorithm);
                long time = handler.sort(elementsToSort);
                if (output == null)
                    output = elementsToSort;

                checkbox.setText(String.format("%s (%dms)", checkbox.getText(), time));
            }
        }

        var outputJoiner = new StringJoiner("\n");
        for (var element : output)
            outputJoiner.add(element);
        outputTextArea.setText(outputJoiner.toString());

        statusLabel.setText("Success!");
    }

    private static final Logger LOGGER;


    // To run GUI without Spring (quick run)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SortingFrame::new);
    }

    static {
        LOGGER = LoggerFactory.getLogger(SortingFrame.class);
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            UIManager.put("TitlePane.unifiedBackground", true);
        } catch (Exception ex) {
            LOGGER.error("Failed to setup GUI", ex);
        }
    }
}
