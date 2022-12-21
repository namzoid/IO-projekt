package pl.put.poznan.sorting.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import com.formdev.flatlaf.FlatIntelliJLaf;
import net.miginfocom.swing.MigLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.logic.SortingAlgorithm;
import pl.put.poznan.sorting.logic.SortingAlgorithmProvider;
import pl.put.poznan.sorting.logic.SortingHandler;

/**
 * Represents main GUI frame.
 */
public class SortingFrame extends JFrame {

    private Map<SortingAlgorithm, JCheckBox> algorithmCheckboxMap = new LinkedHashMap<>();
    private JComboBox<String> elementTypeComboBox;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JLabel statusLabel;
    private DefaultTableModel timeTableModel = new DefaultTableModel();

    /**
     * Construct and show GUI.
     */
    public SortingFrame() {
        setTitle("Sorting Madness");
        setSize(470, 340);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        installComponents();
        setVisible(true);
    }

    /**
     * Add all in-frame components.
     */
    private void installComponents() {
        setLayout(new MigLayout());

        /*
         * | Settings | Input | Output |
         * |       Button + Status     |
         */
        add(createSettingsPanel(), "top, push"); // align on top, pushes other panels
        add(createInputPanel(), "top");
        add(createOutputPanel(), "top, wrap"); // wrap - next panel will be placed on the next row
        add(createButtonAndStatusPanel(), "top, span"); // span - takes 3 columns
    }

    /**
     * Create and fill settings panel consisting of data type and algorithms to select.
     *
     * @return panel
     */
    private JPanel createSettingsPanel() {
        var panel = new JPanel(new MigLayout("wrap")); // wrap - each element will be placed on next row

        // Add elements type selection
        panel.add(new JLabel("Type:"));
        elementTypeComboBox = new JComboBox<>(new String[] {"Integers", "Strings"});
        panel.add(elementTypeComboBox);

        // Add algorithm selection
        panel.add(new JLabel("Algorithms:"));
        SortingAlgorithmProvider
                .getRegisteredAlgorithms()
                .forEach(algorithm -> {
                    // Create checkbox for each algorithm and put in a map for future use
                    var checkbox = new JCheckBox(algorithm.getDisplayName(), false);
                    panel.add(checkbox);
                    algorithmCheckboxMap.put(algorithm, checkbox);
                });

        return panel;
    }

    /**
     * Create and fill input panel consisting of input text area, where user should enter elements to sort.
     *
     * @return panel
     */
    private JPanel createInputPanel() {
        var panel = new JPanel(new MigLayout("wrap"));

        panel.add(new JLabel("Input:"));
        inputTextArea = new JTextArea(13, 10);
        var scrollPane = new JScrollPane(inputTextArea);
        panel.add(scrollPane);

        return panel;
    }

    /**
     * Create and fill output panel consisting of output text area, where sorted elements will show up.
     *
     * @return panel
     */
    private JPanel createOutputPanel() {
        var panel = new JPanel(new MigLayout("wrap"));

        panel.add(new JLabel("Output:"));
        outputTextArea = new JTextArea(13, 10);
        outputTextArea.setEditable(false);
        var scrollPane = new JScrollPane(outputTextArea);
        panel.add(scrollPane);

        return panel;
    }

    /**
     * Create and fill button-status panel consisting of "Sort" button and result status message (label).
     *
     * @return panel
     */
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

    /**
     * Process "Sort" button click.
     */
    private void onSortButtonClick() {
        // Reset output & status message
        outputTextArea.setText(null);
        statusLabel.setText(null);
        algorithmCheckboxMap.forEach((algorithm, checkbox) -> {
            checkbox.setText(algorithm.getDisplayName());
        });

        // Remove empty lines and check if there are elements to sort
        var lines = new LinkedList<>(Arrays.asList(inputTextArea.getText().split("\r?\n")));
        lines.removeIf(String::isBlank);

        if (lines.isEmpty()) {
            statusLabel.setText("Error: Nothing to sort.");
            return;
        }

        // Check if no algorithms selected
        boolean isAnyAlgorithmSelected = algorithmCheckboxMap
                .values().stream()
                .anyMatch(AbstractButton::isSelected);
        if (!isAnyAlgorithmSelected) {
            statusLabel.setText("Error: No algorithms selected.");
            return;
        }

        // Process
        switch (elementTypeComboBox.getSelectedIndex()) {
            // Integers selected
            case -1, 0 -> {
                sortIntegers(lines);
            }
            // Strings selected
            case 1 -> {
                sortStrings(lines);
            }
        }
    }

    /**
     * Sort integers and show the result in GUI.
     *
     * @param lines elements got from input text area
     */
    private void sortIntegers(List<String> lines) {
        // Convert to integers & collect to array
        int i = 0;
        int[] elements = new int[lines.size()];
        for (var rawElement : lines) {
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
            var algorithm = entry.getKey();
            var checkbox = entry.getValue();

            if (checkbox.isSelected()) {
                var elementsToSort = Arrays.copyOf(elements, elements.length);

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

    /**
     * Sort strings and show the result in GUI.
     *
     * @param lines elements got from input text area
     */
    private void sortStrings(List<String> lines) {
        // Collect to array
        var elements = lines.toArray(new String[0]);

        // Sort
        var handler = new SortingHandler();
        String[] output = null;
        for (var entry : algorithmCheckboxMap.entrySet()) {
            var algorithm = entry.getKey();
            var checkbox = entry.getValue();

            if (checkbox.isSelected()) {
                var elementsToSort = Arrays.copyOf(elements, elements.length);

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


    // Used to run GUI without Spring (fast testing / feedback)
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
