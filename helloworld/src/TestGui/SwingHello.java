package TestGui;
import javax.swing.*;
public class SwingHello {
     public static void main(String[] args) {
                try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                }
        JFrame frame = new JFrame("Hello, Swing!");
        final JLabel label = new JLabel("Hello, Swing!");
        frame.getContentPane().add(label);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        frame.setSize(3000, 2000);
        frame.setVisible(true);
}
}