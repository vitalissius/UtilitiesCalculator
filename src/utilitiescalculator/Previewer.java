package utilitiescalculator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Previewer extends JPanel {
    public Previewer() {
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D gg = (Graphics2D) g;

        gg.setColor(Color.RED);
        gg.fillRect(0, 0, getWidth(), getHeight());
    }
}
