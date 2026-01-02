import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class ToDoList extends JFrame {

    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton;
    private JButton deleteButton;

    private static final String FILE_NAME = "tasks.txt";

    public ToDoList() {
        setTitle("To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Dark background =====
        getContentPane().setBackground(new Color(30, 30, 30));

        // ===== Input field =====
        taskField = new JTextField();
        taskField.setFont(new Font("Arial", Font.PLAIN, 18));
        taskField.setBackground(new Color(45, 45, 45));
        taskField.setForeground(Color.WHITE);
        taskField.setCaretColor(Color.WHITE);
        add(taskField, BorderLayout.NORTH);

        // ===== Task list =====
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 18));
        taskList.setBackground(new Color(40, 40, 40));
        taskList.setForeground(Color.WHITE);
        taskList.setSelectionBackground(new Color(70, 70, 70));
        taskList.setSelectionForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.getViewport().setBackground(new Color(40, 40, 40));
        add(scrollPane, BorderLayout.CENTER);

        // ===== Buttons =====
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(30, 30, 30));

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");

        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));

        addButton.setBackground(new Color(60, 60, 60));
        addButton.setForeground(Color.WHITE);

        deleteButton.setBackground(new Color(60, 60, 60));
        deleteButton.setForeground(Color.WHITE);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // ===== Logic =====
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        taskField.addActionListener(e -> addTask());

        loadTasks(); // 🔥 load saved tasks

        setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            listModel.addElement(task);
            taskField.setText("");
            saveTasks();
        }
    }

    private void deleteTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            listModel.remove(index);
            saveTasks();
        }
    }

    private void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < listModel.size(); i++) {
                writer.println(listModel.get(i));
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks");
        }
    }

    private void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                listModel.addElement(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks");
        }
    }

    public static void main(String[] args) {
        new ToDoList();
    }
}
