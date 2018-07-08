package utilitiescalculator.gui.chart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import utilitiescalculator.Dictionary;
import utilitiescalculator.statistics.Statistics;
import utilitiescalculator.statistics.StatisticsReadWriter;

public class ColumnStackedChartPanel extends JPanel implements MouseListener, MouseMotionListener, ComponentListener {

    private static final Color CHART_BACKGROUND_COLOR = Color.WHITE;
    private static final Color CHART_BORDER_COLOR = Color.GRAY;
    private static final Color CHART_GRIDLINES_COLOR = Color.LIGHT_GRAY;
    private static final Color CHART_TEXT_COLOR = Color.BLACK;

    private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final Font PAYMENT_NAMES_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

    private static final Stroke DEFAULT_STROKE = new BasicStroke(1.f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
    private static final Stroke SELECTED_BUTTON_STROKE = new BasicStroke(6.f, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND);
    private static final Stroke UNSELECTED_BUTTON_STROKE = new BasicStroke(1.f, BasicStroke.CAP_ROUND, BasicStroke.CAP_ROUND);

    private final ColumnStackedChart columnStackedChart;

    private final Rectangle[] buttonRectangles;
    private final Rectangle buttonsArea;

    private final Point[][] points;



    public ColumnStackedChartPanel(ColumnStackedChart columnStackedChart) {
        this.columnStackedChart = columnStackedChart;
        buttonRectangles = new Rectangle[this.columnStackedChart.getPaymentsCount()];
        for (int i = 0; i < buttonRectangles.length; i++) {
            buttonRectangles[i] = new Rectangle();
        }
        points = this.columnStackedChart.getPricePoints();
        buttonsArea = new Rectangle(0, 0, this.columnStackedChart.getChartWidth(), this.columnStackedChart.getChartHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gg = (Graphics2D) g;

        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gg.setFont(DEFAULT_FONT);
        gg.setStroke(DEFAULT_STROKE);

        if (needUpdateAttributes) {
            columnStackedChart.updateAttributesByWidthAndHeight(getWidth(), getHeight());
            needUpdateAttributes = false;
        }




        gg.translate(columnStackedChart.getLeftIndent(), columnStackedChart.getTopIndent());


        drawChartPlace(gg);

        drawChart(gg);

        drawPaymentNamesAsButtons(gg);
    }

    private void drawChartPlace(Graphics2D gg) {
        Color previousColor = gg.getColor();
        drawChartBorders(gg);
        drawVerticalChartGridlines(gg);
        drawHorizontalChartGridlines(gg);
        gg.setColor(previousColor);
    }

    private void drawChartBorders(Graphics2D gg) {
        int chartWidth = columnStackedChart.getChartWidth();
        int chartHeight = columnStackedChart.getChartHeight();

        gg.setColor(CHART_BACKGROUND_COLOR);
        gg.fillRect(0, 0, chartWidth, chartHeight);
        gg.setColor(CHART_BORDER_COLOR);
        gg.drawRect(0, 0, chartWidth, chartHeight);
    }

    private void drawVerticalChartGridlines(Graphics2D gg) {
        int monthWidth = columnStackedChart.getMonthWidth();
        int monthCount = columnStackedChart.getMonthsCount();
        int chartHeight = columnStackedChart.getChartHeight();

        for (int i = 0; i < monthCount; i++) {
            int currentx = i * monthWidth;
            if (i > 0) {
                gg.setColor(CHART_GRIDLINES_COLOR);
                gg.drawLine(currentx, 0, i * monthWidth, chartHeight);
            }
            currentx += monthWidth / 2;
            gg.rotate(-Math.PI / 2);
            String word = columnStackedChart.getMonthYear(i);
            Rectangle2D bounds = gg.getFontMetrics(DEFAULT_FONT).getStringBounds(word, gg);
            gg.setColor(CHART_TEXT_COLOR);
            gg.drawString(word, -(chartHeight + (int) bounds.getWidth() + 2), currentx + (int) (bounds.getHeight() / 2));
            gg.rotate(+Math.PI / 2);
        }
    }

    private void drawHorizontalChartGridlines(Graphics2D gg) {
        int chartWidth = columnStackedChart.getChartWidth();
        int chartHeight = columnStackedChart.getChartHeight();
        double oneBillHeight = columnStackedChart.getOneBillHeight();

        int hundredBillHeight = (int) (oneBillHeight * 100);
        int hundredBillMarking = 0;
        for (int i = 0; i < chartHeight; i += hundredBillHeight) {
            if (i > 0) {
                gg.setColor(CHART_GRIDLINES_COLOR);
                gg.drawLine(0, chartHeight - i, chartWidth, chartHeight - i);
            }
            String text = hundredBillMarking + "\u20B4";
            gg.setColor(CHART_TEXT_COLOR);
            gg.drawString(text, -(int) gg.getFontMetrics(getFont()).getStringBounds(text, gg).getWidth(), chartHeight - i);
            hundredBillMarking += 100;
        }
    }



    private static final Color[] CHART_COLORS = {
        new Color(255, 255, 255),   // bottom level - white
        new Color(153, 193, 195),   // electricity
        new Color( 92, 129,  88),
        new Color(239, 106,  71),
        new Color(203,  30,  49),
        new Color( 54,  99, 120),
        new Color(138, 112,  77),
        new Color(254, 213,  89),
        new Color(153, 150,  71),
        new Color(112, 113, 117),
        new Color(250, 205, 208),   // tv
    };

    private int pushedButtonIndex = -1;

    private void drawChart(Graphics2D gg) {
        int monthWidth = columnStackedChart.getMonthWidth();
        int quarter = monthWidth / 4;

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[0].length - 1; j++) {
                Polygon polygon = new Polygon();
                polygon.addPoint(points[i][j].x - quarter, points[i][j].y);
                polygon.addPoint(points[i][j].x - quarter, points[i][j + 1].y);
                polygon.addPoint(points[i][j].x + quarter, points[i][j + 1].y);
                polygon.addPoint(points[i][j].x + quarter, points[i][j].y);

                if (i < points.length - 1 && j == pushedButtonIndex) {
                    drawSelectedPayment(gg, quarter, i, j);
                }

                gg.setColor(CHART_COLORS[j + 1]);
                gg.fillPolygon(polygon);
                gg.setColor(Color.DARK_GRAY);
                gg.drawPolygon(polygon);
            }
        }
    }

    private void drawSelectedPayment(Graphics2D gg, int quarter, int i, int j) {
        Polygon polygon2 = new Polygon();
        polygon2.addPoint(points[i][j].x + quarter, points[i][j].y);
        polygon2.addPoint(points[i][j].x + quarter, points[i][j + 1].y);
        polygon2.addPoint(points[i + 1][j + 1].x - quarter, points[i + 1][j + 1].y);
        polygon2.addPoint(points[i + 1][j].x - quarter, points[i + 1][j].y);
        gg.setColor(CHART_COLORS[j + 1]);
        gg.fillPolygon(polygon2);
        gg.setColor(Color.DARK_GRAY);
        gg.drawPolygon(polygon2);
    }

    private void drawPaymentNamesAsButtons(Graphics2D gg) {
        int chartWidth = columnStackedChart.getChartWidth();
        FontMetrics metrics = gg.getFontMetrics(PAYMENT_NAMES_FONT);
        String[] paymentNames = columnStackedChart.getPaymentNames();
        String longestPaymentName = paymentNames[columnStackedChart.getLongestPaymentNameIndex()];

        Rectangle2D stringBounds = metrics.getStringBounds(longestPaymentName, gg);
        int w = metrics.stringWidth(longestPaymentName);
        int h = metrics.getHeight();
        int arc = 6;

        for (int i = 0; i < paymentNames.length; i++) {

            int horizontalIndent = 20;
            int verticalStep = (int) (h * 1.4);
            int x = chartWidth + horizontalIndent;
            int y = verticalStep * i - (int) stringBounds.getY();

            int rx = x - horizontalIndent / 2;
            int ry = y + (int) stringBounds.getY();
            int rw = w + horizontalIndent;
            int rh = h;

            gg.setColor(CHART_COLORS[i + 1]);
            gg.fillRoundRect(rx, ry, rw, rh, arc, arc);

            if (buttonIndexUnderCursor == i) {
                gg.setStroke(SELECTED_BUTTON_STROKE);
            } else {
                gg.setStroke(UNSELECTED_BUTTON_STROKE);
                gg.setColor(Color.DARK_GRAY);
            }
            gg.drawRoundRect(rx, ry, rw, rh, arc, arc);

            buttonRectangles[i].setBounds(
                    columnStackedChart.getLeftIndent() + rx,
                    columnStackedChart.getTopIndent() + ry + rh - (int) stringBounds.getCenterY(),
                    rw,
                    rh);

            gg.setFont(PAYMENT_NAMES_FONT);
            gg.setColor(CHART_TEXT_COLOR);
            gg.drawString(paymentNames[i], x, y);
        }

        buttonsArea.setBounds(
            (int) buttonRectangles[0].getX(),
            (int) buttonRectangles[0].getY(),
            (int) (buttonRectangles[buttonRectangles.length - 1].getX() + buttonRectangles[buttonRectangles.length - 1].getWidth()),
            (int) (buttonRectangles[buttonRectangles.length - 1].getY() + buttonRectangles[buttonRectangles.length - 1].getHeight()));


    }

    public void setPaymentNames(String[] columnNames) {
        columnStackedChart.setPaymentNames(columnNames);
    }



    /* MouseListener's methods */

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            for (int i = 0; i < buttonRectangles.length; i++) {
                if (buttonRectangles[i].contains(e.getX(), e.getY())) {
                    pushedButtonIndex = i;
                    repaint();
                    return;
                }
            }
        }
        pushedButtonIndex = -1;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // nop
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // nop
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // nop
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // nop
    }



    /* MouseMotionListener's methods */

    private int buttonIndexUnderCursor = -1;
    private boolean needRepaintAfterLostSelection = false;

    @Override
    public void mouseMoved(MouseEvent e) {
        if (buttonsArea.contains(e.getX(), e.getY())) {
            for (int i = 0; i < buttonRectangles.length; i++) {
                if (buttonRectangles[i].contains(e.getX(), e.getY())) {
                    buttonIndexUnderCursor = i;
                    repaint();
                    needRepaintAfterLostSelection = true;
                    return;
                }
            }
        }

        buttonIndexUnderCursor = -1;
        if (needRepaintAfterLostSelection) {
            needRepaintAfterLostSelection = false;
            repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // nop
    }



    /* ComponentListener's methods */

    private boolean needUpdateAttributes = true;

    @Override
    public void componentResized(ComponentEvent e) {
        needUpdateAttributes = true;
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // nop
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // nop
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // nop
    }



    public static void main(String[] args) {
        List<Statistics> stats = new StatisticsReadWriter().read(Paths.get("C:", "Users", "vitalii", ".ucfiles", "statistics.ucs"));

        JFrame frame = new JFrame("title");
        frame.setLayout(new BorderLayout());

        ColumnStackedChartPanel panel = new ColumnStackedChartPanel(new ColumnStackedChart(stats, 50, 160, 60, 50));
        Dictionary DICT = Dictionary.INSTANCE;
        panel.setPaymentNames(new String[]{
            DICT.getWord(Dictionary.Keyword.TC_ELEC),
            DICT.getWord(Dictionary.Keyword.TC_RENT),
            DICT.getWord(Dictionary.Keyword.TC_HEATING),
            DICT.getWord(Dictionary.Keyword.TC_HOT_WATER),
            DICT.getWord(Dictionary.Keyword.TC_COLD_WATER),
            DICT.getWord(Dictionary.Keyword.TC_SEWERAGE),
            DICT.getWord(Dictionary.Keyword.TC_GAS),
            DICT.getWord(Dictionary.Keyword.TC_GARBAGE),
            DICT.getWord(Dictionary.Keyword.TC_INTERCOM),
            DICT.getWord(Dictionary.Keyword.TC_TV),
        });
        frame.add(panel);

        frame.addMouseListener(panel);
        frame.addMouseMotionListener(panel);
        frame.addComponentListener(panel);

        Dimension size = new Dimension(800, 600);
        frame.setPreferredSize(size);
        frame.setSize(size.width, size.height);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
