import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoListApp {
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    
    public ToDoListApp() {
        // Create the main frame
        JFrame frame = new JFrame("To-Do List App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Create the list model and JList
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Create buttons
        JButton addButton = new JButton("Add Task");
        JButton deleteButton = new JButton("Delete Selected Task");
        JButton markButton = new JButton("Mark as Completed");

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 5, 5));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(markButton);

        // Add components to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add button action listeners
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        markButton.addActionListener(e -> markCompleted());

        // Show the frame
        frame.setVisible(true);
    }

    private void addTask() {
        String task = JOptionPane.showInputDialog("Enter Task:");
        if (task != null && !task.trim().isEmpty()) {
            listModel.addElement(task);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter some text!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(null, "No task selected!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void markCompleted() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String task = listModel.getElementAt(selectedIndex);
            if (!task.contains("✔")) {
                listModel.set(selectedIndex, task + " ✔");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No task selected!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListApp::new);
    }
}
