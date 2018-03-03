package utilitiescalculator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Previewer extends JPanel {

    private static final java.awt.Font FONT_TITLE = new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.BOLD, 18);// "Times New Roman"
    private static final java.awt.Font FONT_DATA = new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.PLAIN, 18);
    private static final java.awt.Font FONT_ITEM = new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.PLAIN, 15);
    private static final java.awt.Font FONT_VERTICAL = new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.PLAIN, 17);

    private static final Dictionary DICT = Dictionary.INSTANCE;

    public Previewer() {
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D gg = (Graphics2D) g;

        gg.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        gg.setRenderingHint(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING, java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

        gg.setColor(Color.WHITE);
        gg.fillRect(0, 0, getWidth(), getHeight());


        gg.setColor(java.awt.Color.BLACK);

        gg.drawRect(244, 54, 132, 22);

        gg.drawLine(244, 98, 360 + 244, 98);

        gg.drawLine(128, 98 + 20, 230 + 128, 98 + 20);
        gg.drawLine(406, 98 + 20, 480, 98 + 20);
        gg.drawLine(520, 98 + 20, 604, 98 + 20);

        gg.drawLine(84, 456, 260, 456);
        gg.drawLine(424, 456, 604, 456);

        gg.drawLine(21, 144, 634, 144);
        gg.drawLine(338, 144 + 21, 500, 144 + 21);
        gg.drawLine(21, 144 + 21 * 2, 634, 144 + 21 * 2);
        gg.drawLine(21, 144 + 21 * 3, 634, 144 + 21 * 3);
        gg.drawLine(21, 144 + 21 * 4, 634, 144 + 21 * 4);
        gg.drawLine(21, 144 + 21 * 5, 634, 144 + 21 * 5);
        gg.drawLine(21, 144 + 21 * 6, 634, 144 + 21 * 6);
        gg.drawLine(21, 144 + 21 * 7, 634, 144 + 21 * 7);
        gg.drawLine(21, 144 + 21 * 8, 634, 144 + 21 * 8);
        gg.drawLine(21, 144 + 21 * 9, 634, 144 + 21 * 9);
        gg.drawLine(56, 144 + 21 * 10, 634, 144 + 21 * 10);
        gg.drawLine(56, 144 + 21 * 11, 634, 144 + 21 * 11);
        gg.drawLine(56, 144 + 21 * 12, 634, 144 + 21 * 12);
        gg.drawLine(21, 144 + 21 * 13, 634, 144 + 21 * 13);

        gg.drawLine(21, 144, 21, 417);
        gg.drawLine(56, 334, 56, 417);
        gg.drawLine(170, 144, 170, 417);
        gg.drawLine(212, 144, 212, 417);
        gg.drawLine(254, 144, 254, 417);
        gg.drawLine(338, 144, 338, 417);
        gg.drawLine(419, 165, 419, 417);
        gg.drawLine(500, 144, 500, 417);
        gg.drawLine(567, 144, 567, 417);
        gg.drawLine(634, 144, 634, 417);



        // Draw words
        gg.setFont(FONT_TITLE);


        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_00), 150, 30);   // TITLE


        gg.setFont(FONT_ITEM);
        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_01), 21, 48);    // REQUISITES


        gg.setFont(FONT_DATA);
        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_02), 21, 48 + 22);   // ACCOUNT

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_03), 21, 48 + 23 * 2);  // INITIALS

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_04), 21, 48 + 22 * 3);//ADDRESS

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_05), 90, 48 + 22 * 3);//STREET

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_06), 368, 48 + 22 * 3);//HOUSE

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_07), 490, 48 + 22 * 3);//APARTMENT

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_08), 21, 48 + 22 * 4);//PREFERENCE

        gg.setFont(FONT_ITEM);
        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_09), 50, 48 + 22 * 4 + 32);//SPECIES

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_10), 178, 48 + 22 * 4 + 32);//MONTH

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_11), 222, 48 + 22 * 4 + 32);//YEAR

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_12), 278, 48 + 22 * 4 + 32);//AMOUNT

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_13), 348, 48 + 22 * 4 + 32 - 9);//REGISTRATION

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_14), 356, 48 + 22 * 4 + 32 + 12);//FINITE

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_15), 428, 48 + 22 * 4 + 32 + 12);//INITIAL

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_16), 520, 48 + 22 * 4 + 32);//DIFFERENCE

        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_17), 580, 48 + 22 * 4 + 32);//TARIFF

        gg.drawString(DICT.getWord(Dictionary.Keyword.PAY_ELEC), 26, 48 + 22 * 4 + 32 + 34);//ELECTRIC_POWER
        gg.drawString(DICT.getWord(Dictionary.Keyword.PAY_RENT), 26, 48 + 22 * 4 + 32 + 34 + 21);//RENT
        gg.drawString(DICT.getWord(Dictionary.Keyword.PAY_HEATING), 26, 48 + 22 * 4 + 32 + 34 + 21 * 2);//HEATING
        gg.drawString(DICT.getWord(Dictionary.Keyword.PAY_HOT_WATER), 26, 48 + 22 * 4 + 32 + 34 + 21 * 3);//HOT_WATER
        gg.drawString(DICT.getWord(Dictionary.Keyword.PAY_COLD_WATER), 26, 48 + 22 * 4 + 32 + 34 + 21 * 4);//COLD_WATER
        gg.drawString(DICT.getWord(Dictionary.Keyword.PAY_SEVERAGE), 26, 48 + 22 * 4 + 32 + 34 + 21 * 5);//SEWAGE
        gg.drawString(DICT.getWord(Dictionary.Keyword.PAY_GAS), 26, 48 + 22 * 4 + 32 + 34 + 21 * 6);//NATURAL_GAS

        gg.setFont(FONT_VERTICAL);
        java.awt.geom.AffineTransform orig = gg.getTransform();
        gg.rotate(-Math.PI / 2);
        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_18), -(48 + 22 * 4 + 32 + 34 + 21 * 6 + 64), 26 + 16);//ANOTHER
        gg.setTransform(orig);

        gg.setFont(FONT_ITEM);
        gg.drawString(DICT.getWord(Dictionary.Keyword.PAY_GARBAGE), 60, 48 + 22 * 4 + 32 + 34 + 21 * 7);//GARBAGE_DISPOSAL
        gg.drawString(DICT.getWord(Dictionary.Keyword.PAY_INTERCOM), 60, 48 + 22 * 4 + 32 + 34 + 21 * 8);//INTERCOM
        gg.drawString(DICT.getWord(Dictionary.Keyword.PAY_TV), 60, 48 + 22 * 4 + 32 + 34 + 21 * 9);//TELEVISION

        gg.setFont(FONT_DATA);
        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_19), 21, 48 + 22 * 4 + 32 + 34 + 21 * 9 + 60);//TOTAL
        gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_20), 280, 48 + 22 * 4 + 32 + 34 + 21 * 9 + 60);//SIGNATURE



    }
}
