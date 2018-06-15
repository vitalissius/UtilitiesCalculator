package utilitiescalculator;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import utilitiescalculator.statistics.Statistics;
import utilitiescalculator.statistics.StatisticsReadWriter;

public class Printer {
    private static final Rectangle[] TABLE_COORDS = {
        new Rectangle(244, 54, 132, 22),
        new Rectangle(244, 98, 360 + 244, 98),
        new Rectangle(128, 98 + 20, 230 + 128, 98 + 20),
        new Rectangle(406, 98 + 20, 480, 98 + 20),
        new Rectangle(520, 98 + 20, 604, 98 + 20),
        new Rectangle(84, 456, 260, 456),
        new Rectangle(424, 456, 604, 456),
        new Rectangle(21, 144, 634, 144),
        new Rectangle(338, 144 + 21, 500, 144 + 21),
        new Rectangle(21, 144 + 21 * 2, 634, 144 + 21 * 2),
        new Rectangle(21, 144 + 21 * 3, 634, 144 + 21 * 3),
        new Rectangle(21, 144 + 21 * 4, 634, 144 + 21 * 4),
        new Rectangle(21, 144 + 21 * 5, 634, 144 + 21 * 5),
        new Rectangle(21, 144 + 21 * 6, 634, 144 + 21 * 6),
        new Rectangle(21, 144 + 21 * 7, 634, 144 + 21 * 7),
        new Rectangle(21, 144 + 21 * 8, 634, 144 + 21 * 8),
        new Rectangle(21, 144 + 21 * 9, 634, 144 + 21 * 9),
        new Rectangle(56, 144 + 21 * 10, 634, 144 + 21 * 10),
        new Rectangle(56, 144 + 21 * 11, 634, 144 + 21 * 11),
        new Rectangle(56, 144 + 21 * 12, 634, 144 + 21 * 12),
        new Rectangle(21, 144 + 21 * 13, 634, 144 + 21 * 13),
        new Rectangle(21, 144, 21, 417),
        new Rectangle(56, 334, 56, 417),
        new Rectangle(170, 144, 170, 417),
        new Rectangle(212, 144, 212, 417),
        new Rectangle(254, 144, 254, 417),
        new Rectangle(338, 144, 338, 417),
        new Rectangle(420, 165, 420, 417),
        new Rectangle(500, 144, 500, 417),
        new Rectangle(568, 144, 568, 417),
        new Rectangle(634, 144, 634, 417),
    };

    private static final Map<Dictionary.Keyword, Point> FIELDS_XY =
            new EnumMap<Dictionary.Keyword, Point>(Dictionary.Keyword.class){{
        // title
        put(Dictionary.Keyword.LINE_TITLE, new Point(150, 30));
        // vertical
        put(Dictionary.Keyword.LINE_OTHER, new Point(-(48 + 22 * 4 + 32 + 34 + 21 * 6 + 64), 26 + 16));
        // items
        put(Dictionary.Keyword.LINE_REQUISITES, new Point(21, 48));
        put(Dictionary.Keyword.LINE_SPECIES, new Point(50, 48 + 22 * 4 + 32));
        put(Dictionary.Keyword.LINE_MONTH, new Point(178, 48 + 22 * 4 + 32));
        put(Dictionary.Keyword.LINE_YEAR, new Point(222, 48 + 22 * 4 + 32));
        put(Dictionary.Keyword.LINE_AMOUNT, new Point(278, 48 + 22 * 4 + 32));
        put(Dictionary.Keyword.LINE_REGISTRATION, new Point(348, 48 + 22 * 4 + 32 - 9));
        put(Dictionary.Keyword.LINE_FINITE, new Point(356, 48 + 22 * 4 + 32 + 12));
        put(Dictionary.Keyword.LINE_INITIAL, new Point(428, 48 + 22 * 4 + 32 + 12));
        put(Dictionary.Keyword.LINE_DIFFERENCE, new Point(512, 48 + 22 * 4 + 32));
        put(Dictionary.Keyword.LINE_TARIFF, new Point(580, 48 + 22 * 4 + 32));
        put(Dictionary.Keyword.LINE_ELEC, new Point(26, 48 + 22 * 4 + 32 + 34));
        put(Dictionary.Keyword.LINE_RENT, new Point(26, 48 + 22 * 4 + 32 + 34 + 21));
        put(Dictionary.Keyword.LINE_HEATING, new Point(26, 48 + 22 * 4 + 32 + 34 + 21 * 2));
        put(Dictionary.Keyword.LINE_HOT_WATER, new Point(26, 48 + 22 * 4 + 32 + 34 + 21 * 3));
        put(Dictionary.Keyword.LINE_COLD_WATER, new Point(26, 48 + 22 * 4 + 32 + 34 + 21 * 4));
        put(Dictionary.Keyword.LINE_SEVERAGE, new Point(26, 48 + 22 * 4 + 32 + 34 + 21 * 5));
        put(Dictionary.Keyword.LINE_GAS, new Point(26, 48 + 22 * 4 + 32 + 34 + 21 * 6));
        put(Dictionary.Keyword.LINE_GARBAGE, new Point(60, 48 + 22 * 4 + 32 + 34 + 21 * 7));
        put(Dictionary.Keyword.LINE_INTERCOM, new Point(60, 48 + 22 * 4 + 32 + 34 + 21 * 8));
        put(Dictionary.Keyword.LINE_TV, new Point(60, 48 + 22 * 4 + 32 + 34 + 21 * 9));
        // data
        put(Dictionary.Keyword.LINE_ACCOUNT, new Point(21, 48 + 22));
        put(Dictionary.Keyword.LINE_INITIALS, new Point(21, 48 + 23 * 2));
        put(Dictionary.Keyword.LINE_ADDRESS, new Point(21, 48 + 22 * 3));
        put(Dictionary.Keyword.LINE_STREET, new Point(90, 48 + 22 * 3));
        put(Dictionary.Keyword.LINE_BUILDING, new Point(368, 48 + 22 * 3));
        put(Dictionary.Keyword.LINE_APARTMENT, new Point(490, 48 + 22 * 3));
        put(Dictionary.Keyword.LINE_PRIVILEGE, new Point(21, 48 + 22 * 4));
        put(Dictionary.Keyword.LINE_TOTAL, new Point(21, 48 + 22 * 4 + 32 + 34 + 21 * 9 + 60));
        put(Dictionary.Keyword.LINE_SIGNATURE, new Point(280, 48 + 22 * 4 + 32 + 34 + 21 * 9 + 60));
    }};

    private static final Dictionary DICT = Dictionary.INSTANCE;
    private static final Settings SETT = Settings.INSTANCE;

    private static final java.awt.Font FONT_TITLE = new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.BOLD, 18);
    private static final java.awt.Font FONT_DATA = new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.PLAIN, 18);
    private static final java.awt.Font FONT_ITEM = new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.PLAIN, 15);
    private static final java.awt.Font FONT_VERTICAL = new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.PLAIN, 17);

    private final DecimalFormat format;

    private Printer() {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setDecimalSeparator('-');
        format = new DecimalFormat("0.00", symbols);
    }

    private static volatile Printer instance;

    public static Printer getInstance() {
        Printer inst = Printer.instance;
        if (inst == null) {
            synchronized (Printer.class) {
                inst = Printer.instance;
                if (inst == null) {
                    Printer.instance = inst = new Printer();
                }
            }
        }
        return inst;
    }

    public void printPreview(Graphics2D gg) {
        printTable(gg);
        printFields(gg);
        printInserts(gg);
    }

    private void printTable(Graphics2D gg) {
        gg.drawRect(244, 54, 132, 22);

        for (int i = 1; i < TABLE_COORDS.length; i++) {
            Rectangle c = TABLE_COORDS[i];
            gg.drawLine(c.x, c.y, c.width, c.height);
        }
    }

    private void printFields(Graphics2D gg) {
        gg.setFont(FONT_TITLE);
        Dictionary.Keyword key = Dictionary.Keyword.LINE_TITLE;
        Point point = FIELDS_XY.get(key);
        gg.drawString(DICT.getWord(key), point.x, point.y);

        gg.setFont(FONT_VERTICAL);
        java.awt.geom.AffineTransform orig = gg.getTransform();
        gg.rotate(-Math.PI / 2);
        key = Dictionary.Keyword.LINE_OTHER;
        point = FIELDS_XY.get(key);
        gg.drawString(DICT.getWord(key), point.x, point.y);
        gg.setTransform(orig);

        List<Dictionary.Keyword> keys = new ArrayList<>(FIELDS_XY.keySet());

        gg.setFont(FONT_ITEM);
        keys.subList(keys.indexOf(Dictionary.Keyword.LINE_REQUISITES),
                keys.indexOf(Dictionary.Keyword.LINE_TV) + 1).forEach((k) -> {
            Point p = FIELDS_XY.get(k);
            gg.drawString(DICT.getWord(k), p.x, p.y);
        });

        gg.setFont(FONT_DATA);
        keys.subList(keys.indexOf(Dictionary.Keyword.LINE_ACCOUNT),
                keys.indexOf(Dictionary.Keyword.LINE_SIGNATURE) + 1).forEach((k) -> {
            Point p = FIELDS_XY.get(k);
            gg.drawString(DICT.getWord(k), p.x, p.y);
        });
    }

    private void printInserts(Graphics2D gg) {
        gg.setFont(FONT_TITLE);
        gg.drawString(SETT.getPersonalAccount(), 272, 48 + 22 + 1);
        gg.drawString(format.format(SETT.getTotal()), 120, 48 + 22 * 4 + 32 + 34 + 21 * 9 + 60);

        gg.setFont(FONT_DATA);
        String fullName = SETT.getPersonalSurname() + " " + SETT.getPersonalFirstName() + " " + SETT.getPersonalPatronymic();
        gg.drawString(fullName, 250, 48 + 23 * 2);

        gg.drawString(SETT.getPersonalStreet(), 140, 48 + 22 * 3);
        gg.drawString(SETT.getPersonalBuilding(), 420, 48 + 22 * 3);
        gg.drawString(SETT.getPersonalApartment(), 530, 48 + 22 * 3);

        gg.drawString("" + SETT.getElecPrivilege(), 100, 48 + 22 * 4);

        gg.setFont(FONT_ITEM);
        printElec(gg);
        printRent(gg);
        printHeating(gg);
        printHotWater(gg);
        printColdWater(gg);
        printSeverage(gg);
        printGas(gg);
        printGarbage(gg);
        printIntercom(gg);
        printTv(gg);
    }

    private final int first = TABLE_COORDS[TABLE_COORDS.length - 1 - 1 - 6].x;  //170;
    private final int second = TABLE_COORDS[TABLE_COORDS.length - 1 - 1 - 5].x; //212;
    private final int third = TABLE_COORDS[TABLE_COORDS.length - 1 - 1 - 4].x;  //254;
    private final int fourth = TABLE_COORDS[TABLE_COORDS.length - 1 - 1 - 3].x; //338;
    private final int fifth = TABLE_COORDS[TABLE_COORDS.length - 1 - 1 - 2].x;  //420;
    private final int sixth = TABLE_COORDS[TABLE_COORDS.length - 1 - 1 - 1].x;  //500;
    private final int seventh = TABLE_COORDS[TABLE_COORDS.length - 1 - 1 - 0].x;//568;

    /*
            +--------+-----+----+------+------------+----+
            |        |     |    |      |    meter   |    |
            |        |     |    |      +------------|    |
            |        |month|year|amount|finite|begin|diff|
            +--------+-----+----+------+------+-----+----+
            |        |     |    |      |      |     |    |
            +--------+-----+----+------+------+-----+----+
            |        |1st  |    |      |      |     |    |
            |        |     |2nd |      |      |     |    |
            |        |     |    |3rd   |      |     |    |
            |        |     |    |      |4th   |     |    |
            |        |     |    |      |      |5th  |    |
            |        |     |    |      |      |     |6th |
            |        |     |    |      |      |     |    |7th border
            +--------+-----+----+------+------+-----+----+

    */

    private void printElec(Graphics2D gg) {
        int y = Printer.FIELDS_XY.get(Dictionary.Keyword.LINE_ELEC).y;
        printMonthAndYear(gg, y);
        if (SETT.getUsedElec()) {
            _printWithinBorders(gg, format.format(SETT.getPaymentsElec()), third, fourth, y);
        }
        _printWithinBorders(gg, SETT.getLineElecEnd(), fourth, fifth, y);
        _printWithinBorders(gg, SETT.getLineElecBegin(), fifth, sixth, y);
        _printWithinBorders(gg, "" + SETT.getElecTotal(), sixth, seventh, y);
    }

    private void printRent(Graphics2D gg) {
        _printOther(gg, SETT.getUsedRent(), format.format(SETT.getPaymentsRent()),
                FIELDS_XY.get(Dictionary.Keyword.LINE_RENT).y);
    }

    private void printHeating(Graphics2D gg) {
        _printOther(gg, SETT.getUsedHeating(), format.format(SETT.getPaymentsHeating()),
                FIELDS_XY.get(Dictionary.Keyword.LINE_HEATING).y);
    }

    private void printHotWater(Graphics2D gg) {
        _printOther(gg, SETT.getUsedHotWater(), format.format(SETT.getPaymentsHotWater()),
                FIELDS_XY.get(Dictionary.Keyword.LINE_HOT_WATER).y);
    }

    private void printColdWater(Graphics2D gg) {
        _printOther(gg, SETT.getUsedColdWater(), format.format(SETT.getPaymentsColdWater()),
                FIELDS_XY.get(Dictionary.Keyword.LINE_COLD_WATER).y);
    }

    private void printSeverage(Graphics2D gg) {
        _printOther(gg, SETT.getUsedSeverage(), format.format(SETT.getPaymentsSeverage()),
                FIELDS_XY.get(Dictionary.Keyword.LINE_SEVERAGE).y);
    }

    private void printGas(Graphics2D gg) {
        int y = FIELDS_XY.get(Dictionary.Keyword.LINE_GAS).y;
        printMonthAndYear(gg, y);
        if (SETT.getUsedElec()) {
            _printWithinBorders(gg, format.format(SETT.getPaymentsGas()), third, fourth, y);
        }
        _printWithinBorders(gg, SETT.getLineGasEnd(), fourth, fifth, y);
        _printWithinBorders(gg, SETT.getLineGasBegin(), fifth, sixth, y);
        _printWithinBorders(gg, "" + SETT.getGasTotal(), sixth, seventh, y);
    }

    private void printGarbage(Graphics2D gg) {
        _printOther(gg, SETT.getUsedGarbage(), format.format(SETT.getPaymentsGarbage()),
                FIELDS_XY.get(Dictionary.Keyword.LINE_GARBAGE).y);
    }

    private void printIntercom(Graphics2D gg) {
        _printOther(gg, SETT.getUsedIntercom(), format.format(SETT.getPaymentsIntercom()),
                FIELDS_XY.get(Dictionary.Keyword.LINE_INTERCOM).y);
    }

    private void printTv(Graphics2D gg) {
        _printOther(gg, SETT.getUsedTv(), format.format(SETT.getPaymentsTv()),
                FIELDS_XY.get(Dictionary.Keyword.LINE_TV).y);
    }

    private void printMonthAndYear(Graphics2D gg, int y) {
        String str = SETT.getLineMonth().substring(0, 2);
        int centerX = (int) FONT_ITEM.getStringBounds(str, gg.getFontRenderContext()).getCenterX();
        int x = first + (second - first) / 2 - centerX;
        gg.drawString(str, x, y);

        str = SETT.getLineYear();
        centerX = (int) FONT_ITEM.getStringBounds(str, gg.getFontRenderContext()).getCenterX();
        x = second + (third - second) / 2 - centerX;
        gg.drawString(str, x, y);
    }

    private void _printWithinBorders(Graphics2D gg, String s, int leftBorder, int rightBorder, int y) {
        Rectangle2D rect = FONT_ITEM.getStringBounds(s, gg.getFontRenderContext());
        gg.drawString(s, (leftBorder + (rightBorder - leftBorder) / 2) - (int) (rect.getCenterX()), y);
    }

    private void _printOther(Graphics2D gg, boolean isUsed, String s, int y) {
        if (isUsed) {
            printMonthAndYear(gg, y);
            int centerX = (int) FONT_ITEM.getStringBounds(s, gg.getFontRenderContext()).getCenterX();
            gg.drawString(s, (third + (fourth - third) / 2) - centerX, y);
        }
    }



    public static class Report implements Printable {
        @Override public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) {
            if (pageIndex > Printable.PAGE_EXISTS) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D gg = (Graphics2D) graphics;

            gg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            gg.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);

            gg.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            final Font font = new Font(Font.SERIF, Font.ITALIC | Font.BOLD, 13);
            gg.setFont(font);
            gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_NOTICE), 5, 45);
            gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_TELLER), 5, 275);
            gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_RECEIPT), 5, 395);
            gg.drawString(DICT.getWord(Dictionary.Keyword.LINE_TELLER), 5, 625);

            gg.setStroke(new BasicStroke(0.3f));
            gg.drawLine(90, 35, 90, 350);
            gg.drawLine(90, 380, 90, 700);
            gg.drawLine(10, 365, 520, 365);


            Printer printer = new Printer();
            gg.translate(86, 23);
            gg.setStroke(new BasicStroke(0.5f));
            gg.scale(0.7, 0.7);
            printer.printPreview(gg);

            gg.scale(1 / 0.7, 1 / 0.7);
            gg.translate(0, 455 - (120 - 23) - 7);
            gg.scale(0.7, 0.7);
            printer.printPreview(gg);

            return Printable.PAGE_EXISTS;
        }

        private Paper makePaper(PaperSize paperSize, PaperMargins paperMargins) {
            double width = paperSize.getWidth() * PPI;
            double height = paperSize.getHeight() * PPI;
            double left = paperMargins.getLeftMargin() * PPI;
            double right = paperMargins.getRightMargin() * PPI;
            double top = paperMargins.getTopMargin() * PPI;
            double bottom = paperMargins.getBottomMargin() * PPI;

            Paper paper = new Paper();
            paper.setSize(width, height);
            paper.setImageableArea(left, top, width - left - right, height - top - bottom);
            return paper;
        }

        public void printReport() {
            PageFormat pf = new PageFormat();
            pf.setPaper(this.makePaper(PaperSize.A4, PaperMargins.NARROW2));

            Book b = new Book();
            b.append(new Report(), pf);

            PrinterJob pj = PrinterJob.getPrinterJob();
            pj.setPageable(b);

            if (pj.printDialog()) {
                try {
                    pj.print();
                    saveStatistics();
                } catch (PrinterException e) {
                    // ignore
                }
            }
        }

        private void saveStatistics() {
            Statistics.Builder sb = new Statistics.Builder();

            sb.timestamp(new Date().getTime());
            sb.month(Integer.parseInt(SETT.getLineMonth().substring(0, 2)));
            sb.year(Integer.parseInt(SETT.getLineYear()));

            if (SETT.getUsedElec()) {
                sb.electricity(SETT.getElecTotal(), SETT.getPaymentsElec());
            }
            if (SETT.getUsedRent()) {
                sb.rent(SETT.getPaymentsRent());
            }
            if (SETT.getUsedHeating()) {
                sb.heating(SETT.getPaymentsHeating());
            }
            if (SETT.getUsedHotWater()) {
                sb.hotWater(SETT.getPaymentsHotWater());
            }
            if (SETT.getUsedColdWater()) {
                sb.coldWater(SETT.getPaymentsColdWater());
            }
            if (SETT.getUsedSeverage()) {
                sb.sewerage(SETT.getPaymentsSeverage());
            }
            if (SETT.getUsedGas()) {
                sb.gas(SETT.getGasTotal(), SETT.getPaymentsGas());
            }
            if (SETT.getUsedGarbage()) {
                sb.garbage(SETT.getPaymentsGarbage());
            }
            if (SETT.getUsedIntercom()) {
                sb.intercom(SETT.getPaymentsIntercom());
            }
            if (SETT.getUsedTv()) {
                sb.tv(SETT.getPaymentsTv());
            }

            new StatisticsReadWriter().write(sb.build());
        }

        private static final double PPI = 72;
    }
}

enum PaperMargins {
    NARROW(0.5, 0.5, 0.5, 0.5),
    NARROW2(0.5, 0.5, 0.4, 0.5);

    private final double leftMargin;
    private final double topMargin;
    private final double rightMargin;
    private final double bottomMargin;

    private PaperMargins(double leftMargin, double topMargin, double rightMargin, double bottomMargin) {
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
        this.rightMargin = rightMargin;
        this.bottomMargin = bottomMargin;
    }

    public double getLeftMargin() {
        return leftMargin;
    }

    public double getTopMargin() {
        return topMargin;
    }

    public double getRightMargin() {
        return rightMargin;
    }

    public double getBottomMargin() {
        return bottomMargin;
    }
}



enum PaperSize {
    A4(8.3, 11.7);

    private final double width;
    private final double height;

    private PaperSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}