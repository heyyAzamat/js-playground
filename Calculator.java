import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    
    private JTextField display;
    private StringBuilder input = new StringBuilder();

    public Calculator() {
        setTitle("Java Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 26));
        add(display, BorderLayout.NORTH);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        
        String[] buttons = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "C", "=", "/"
        };

        for (String b : buttons) {
            JButton btn = new JButton(b);
            btn.setFont(new Font("Arial", Font.BOLD, 24));

            btn.setBackground(Color.DARK_GRAY);
            btn.setForeground(Color.WHITE);

            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String click = e.getActionCommand();

        if (click.equals("C")) {
            input.setLength(0);
            display.setText("");
            return;
        }

        if (click.equals("=")) {
            try {
                double result = eval(input.toString());
                display.setText(String.valueOf(result));
                input.setLength(0);
                input.append(result);
            } catch (Exception ex) {
                display.setText("Error");
            }
            return;
        }

        input.append(click);
        display.setText(input.toString());
    }

    public static double eval(String expr) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double result = parseExpression();
                return result;
            }

            double parseExpression() {
                double result = parseTerm();
                for (;;) {
                    if      (eat('+')) result += parseTerm();
                    else if (eat('-')) result -= parseTerm();
                    else return result;
                }
            }

            double parseTerm() {
                double result = parseFactor();
                for (;;) {
                    if      (eat('*')) result *= parseFactor();
                    else if (eat('/')) result /= parseFactor();
                    else return result;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double result;
                int startPos = pos;
                while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                result = Double.parseDouble(expr.substring(startPos, pos));

                return result;
            }
        }.parse();
    }


    public static void main(String[] args) {
        new Calculator();
    }

}
