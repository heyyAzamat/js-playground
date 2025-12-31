import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class FormulaGraph extends JPanel {
    
    private String formula;

    public FormulaGraph(String formula) {
        this.formula = formula.replace(" ", " ").toLowerCase();
    }

    private double calcY(double x) {
        String f = formula.replace("y=", "");

        if (f.startsWith("x")) f = "1" + f;

        int xIndex = f.indexOf("x");

        double m = 0;
        double b = 0;

        if (xIndex != -1) {
            String mStr = f.substring(0, xIndex);
            if (mStr.equals("") || mStr.equals("+")) m = 1;
            else if (mStr.equals("-")) m = -1;
            else m = Double.parseDouble(mStr);

            String bStr = f.substring(xIndex + 1);
            if (!bStr.equals("")) b = Double.parseDouble(bStr);
        } else {
            b = Double.parseDouble(f);
        }

        return m * x + b;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int w = getWidth();
        int h = getHeight();

        g.drawLine(w / 2, 0, w / 2, h);
        g.drawLine(0, h / 2, w, h / 2);

        for (int px = -w / 2; px < w / 2; px++) {
            double x = px / 50.0;
            double yValue = calcY(x);
            int y = (int) (yValue * 50);
            g.fillOval(px + w / 2, h / 2, 2, 2);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the formula: ");
        String f = scanner.nextLine();

        JFrame frame = new JFrame("Graph Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(new FormulaGraph(f));
        frame.setVisible(true);
    }
}
