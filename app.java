package src;
import javax.swing.*;
public class app {
    public static void main(String[] args) {
        int bw=600;
        int bh=bw;
        
        JFrame f=new JFrame("Snake");
        f.setVisible(true);
        f.setSize(bw,bh);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Snake s=new Snake(bw, bh);
        f.add(s);
        f.pack();
        s.requestFocus();
    }
}
