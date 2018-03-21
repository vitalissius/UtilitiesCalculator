package utilitiescalculator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class Previewer extends JPanel {
    private static final Printer PRINTER = new Printer();

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D gg = (Graphics2D) g;

        gg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        gg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        gg.setColor(Color.WHITE);
        gg.fillRect(0, 0, this.getWidth(), this.getHeight());

        gg.setColor(Color.BLACK);

        PRINTER.printPreview(gg);
    }
}
