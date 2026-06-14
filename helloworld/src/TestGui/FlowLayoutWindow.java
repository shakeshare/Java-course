package TestGui;
import javax.swing.*; 
import java.awt.*;
public class FlowLayoutWindow extends JFrame {
    public FlowLayoutWindow() {
        setTitle("FlowLayout Window");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        // Add some buttons to demonstrate the flow layout
        add(new JButton("Button 1"));
        add(new JButton("Button 2"));
        add(new JButton("Button 3"));
        add(new JButton("Button 4"));
        add(new JButton("Button 5"));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlowLayoutWindow window = new FlowLayoutWindow();
            window.pack();
            window.setVisible(true);
        });
    }
      
}
