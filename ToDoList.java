import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ToDoList extends JFrame {

    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton;
    private JButton deleteButton;

    public ToDoList() {
        setTitle("To-Do List");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Верхняя панель (поле ввода) =====
        taskField = new JTextField();
        taskField.setFont(new Font("Arial", Font.PLAIN, 18));
        add(taskField, BorderLayout.NORTH);

        // ===== Список задач =====
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 18));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        // ===== Кнопки =====
        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");

        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ===== Логика =====
        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());

        // Enter = добавить задачу
        taskField.addActionListener(e -> addTask());

        setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            listModel.addElement(task);
            taskField.setText("");
        }
    }

    private void deleteTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            listModel.remove(index);
        }
    }

    public static void main(String[] args) {
        new ToDoList();
    }
}
