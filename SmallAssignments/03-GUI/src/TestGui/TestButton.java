package TestGui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TestButton {
    public static void main(String[] args) {
        JFrame f = new JFrame("Test Button");
        f.setSize(300, 200);
        f.setLayout(new FlowLayout(FlowLayout.CENTER));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton b = new JButton("Click Me!");
        b.addActionListener(new ButtonHandler());
        f.add(b);
        f.setVisible(true);
    }
    static class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button was clicked!");
        }
    }
}
