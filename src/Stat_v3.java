
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import utilitiescalculator.statistics.Statistics;
import utilitiescalculator.statistics.StatisticsReadWriter;

public class Stat_v3 {
    public static void main(String[] args) {

        List<Statistics> stats = new StatisticsReadWriter().read(Paths.get("C:", "Users", "vitalii", ".ucfiles", "statistics.ucs"));

        JFrame frame = new JFrame("title");
        frame.setLayout(new BorderLayout());

        ColumnStackedChartPanel panel = new ColumnStackedChartPanel(stats);
        frame.add(panel);
        frame.addMouseListener(panel);
        frame.addMouseMotionListener(panel);
        Dimension size = new Dimension(800, 600);
        frame.setPreferredSize(size);
        frame.setSize(size.width, size.height);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class ColumnStackedChartPanel extends JPanel implements MouseListener, MouseMotionListener  {

    private final List<Statistics> stats;

    public ColumnStackedChartPanel(List<Statistics> stats) {
        super();
        int yearAndOneMonth = 13;
        if (stats.size() > yearAndOneMonth) {
            this.stats = stats.subList(stats.size() - yearAndOneMonth, stats.size());
        } else {
            this.stats = stats;
        }
//        this.stats.sort((Statistics o1, Statistics o2) -> Long.compare(o2.getTimestamp(), o1.getTimestamp()));
        monthsCount = this.stats.size();
        prices = new double[monthsCount][pricesCount];
        for (int i = 0; i < prices.length; i++) {
            prices[i][0] = 0;
            prices[i][1] = this.stats.get(i).getElectricity().getPrice();
            prices[i][2] = this.stats.get(i).getRent();
            prices[i][3] = this.stats.get(i).getHeating();
            prices[i][4] = this.stats.get(i).getHotWater();
            prices[i][5] = this.stats.get(i).getColdWater();
            prices[i][6] = this.stats.get(i).getSewerage();
            prices[i][7] = this.stats.get(i).getGas().getPrice();
            prices[i][8] = this.stats.get(i).getGarbage();
            prices[i][9] = this.stats.get(i).getIntercom();
            prices[i][10] = this.stats.get(i).getTv();
        }
//        for (double[] p : prices) {
//            System.out.println(Arrays.toString(p));
//        }
        ceiling = ceiling(this.stats);
    }

    private final int rightIndent = 150;
    private final int leftIndent = 50;
    private final int topIndent = 50;
    private final int bottomIndent = 50;

    private int index = -1;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            for (int i = 0; i < rects.length; i++) {
                if (rects[i].contains(e.getX(), e.getY())) {

                    System.out.println("x:" + e.getX() + ", y:" + e.getY());
                    System.out.println(rects[i]);


                    index = i;
                    repaint();
                    return;
                }
                //
            }
        }
//        needRepaint = false;
        index = -1;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    private Color color = Color.BLACK;
    private int indexMove = -1;
    private boolean needRepaint = false;

    @Override
    public void mouseMoved(MouseEvent e) {
        for (int i = 0; i < rects.length; i++) {
            if (rects[i].contains(e.getX(), e.getY())) {
                System.out.println("color");
                color = Color.WHITE;
                indexMove = i;
                needRepaint = true;
                repaint();
                return;
            }
//            needRepaint = false;
        }
        indexMove = -1;
        if (needRepaint) {
            System.out.println("black");
            color = Color.BLACK;
            needRepaint = false;
            repaint();
        }
    }

    private enum Price {
        Elec, Rent, Heating, HotWater, ColdWater, Sewerage, Gas, Garbage, Intercom, Tv
    }

    private final int pricesCount = 10 + 1;
    private final int monthsCount;

    private final int ceiling;

    private int height;
    private int width;

    private int ceiling(List<Statistics> stats) {
        double maxSum = 0;
        for (Statistics s : stats) {
            double sum = s.getElectricity().getPrice() + s.getRent() + s.getHeating() + s.getHotWater() +
                    s.getColdWater() + s.getSewerage() + s.getGas().getPrice() + s.getGarbage() + s.getIntercom() +
                    s.getTv();
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        double a = 100 - (maxSum % 100);
        return (int) (maxSum + a);
    }

    private static final Color[] COLORS = {
        new Color(255, 255, 255),
        new Color(153, 193, 195),
        new Color( 92, 129,  88),
        new Color(239, 106,  71),
        new Color(203,  30,  49),
        new Color( 54,  99, 120),
        new Color(138, 112,  77),
        new Color(254, 213,  89),
        new Color(153, 150,  71),
        new Color(112, 113, 117),
        new Color(250, 205, 208),
    };

    private final double rightAngle = Math.PI / 2;

    private void drawBorders(Graphics2D gg, int chartWidth, int chartHeight) {
        gg.drawLine(0, 0, 0, chartHeight);//left
        gg.drawLine(0, chartHeight, chartWidth, chartHeight);//bottom
        gg.drawLine(chartWidth, chartHeight, chartWidth, 0);//right
        gg.drawLine(chartWidth, 0, 0, 0);//top
    }
    private void drawPins(Graphics2D gg, int chartHeight, int oneMonthWidth) {
        Color prevColor = gg.getColor();
        gg.setColor(Color.RED);
        for (int i = 0; i <= monthsCount; i++) {
            int currentx = i * oneMonthWidth;
            gg.drawLine(currentx, chartHeight, currentx, chartHeight - 20);
        }
        gg.setColor(prevColor);
    }
    private void drawStacks(Graphics2D gg, Color color, int currentx, int currenty1, int currenty2) {
        gg.setColor(color);
        gg.drawLine(currentx, currenty1, currentx, currenty2);
    }

    private final double[][] prices;

    private final Rectangle[] rects = new Rectangle[pricesCount - 1];

    private final Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gg = (Graphics2D) g;
        gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //setBackground(Color.WHITE);


        gg.translate(leftIndent, topIndent);

        height = getHeight();
        width = getWidth();


        int chartHeight = (int) (((double) (height - (topIndent + bottomIndent)) / ceiling) * ceiling);
        int chartWidth = ((width - (leftIndent + rightIndent)) / monthsCount) * monthsCount;

        Color previousColor = gg.getColor();
        gg.setColor(Color.WHITE);
        gg.fillRect(0, 0, chartWidth, chartHeight);
        gg.setColor(previousColor);

        int oneMonthWidth = chartWidth / monthsCount;

        drawBorders(gg, chartWidth, chartHeight);
        drawPins(gg, chartHeight, oneMonthWidth);


        double one$ = ((double) chartHeight) / ceiling;

        //gg.setStroke(new BasicStroke(oneMonthWidth / 2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));


        Point[][] points = new Point[prices.length][prices[0].length];
        //System.out.println(points.length);
        //System.out.println(points[0].length);
//        for (int i = 0; i < points.length; i++) {
//            int x = i * oneMonthWidth + oneMonthWidth / 2;
//            points[i][0] = new Point(x, chartHeight);
//        }

        for (int i = 0; i < prices.length; i++) {
            int x = i * oneMonthWidth + oneMonthWidth / 2;
            int y1 = chartHeight;
            for (int j = 0; j < prices[0].length; j++) {
                gg.setColor(COLORS[j]);
                int y2 = (int) (y1 - Math.ceil(prices[i][j] * one$));
                points[i][j] = new Point(x, y2);
                gg.fillOval(x, y2, 3, 3);
                y1 = y2;
            }
        }

//        for (Point[] dd : points) {
//            System.out.println(Arrays.toString(dd));
//        }


        int quarter = oneMonthWidth / 4;

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points[0].length - 1; j++) {
                Polygon polygon = new Polygon();
                polygon.addPoint(points[i][j].x - quarter, points[i][j].y);
                polygon.addPoint(points[i][j].x - quarter, points[i][j + 1].y);
                polygon.addPoint(points[i][j].x + quarter, points[i][j + 1].y);
                polygon.addPoint(points[i][j].x + quarter, points[i][j].y);

                if (i < points.length - 1 && j == index) {//i < points.length - 1 && j < points[0].length - 1) {
                    Polygon polygon2 = new Polygon();
                    polygon2.addPoint(points[i][j].x + quarter, points[i][j].y);
                    polygon2.addPoint(points[i][j].x + quarter, points[i][j + 1].y);
                    polygon2.addPoint(points[i + 1][j + 1].x - quarter, points[i + 1][j + 1].y);
                    polygon2.addPoint(points[i + 1][j].x - quarter, points[i + 1][j].y);
                    gg.setColor(COLORS[j + 1]);
                    gg.fillPolygon(polygon2);
                    gg.setColor(Color.DARK_GRAY);
                    gg.drawPolygon(polygon2);
                }
                gg.setColor(COLORS[j + 1]);
                gg.fillPolygon(polygon);
                gg.setColor(Color.DARK_GRAY);
                gg.drawPolygon(polygon);
            }
        }

        String[] words = {
            "Електроенергія",
            "Квартплата",
            "Опалення",
            "Гаряча вода",
            "Холодна вода",
            "Каналізація",
            "Газ природний",
            "Вивіз сміття",
            "Домофон",
            "Телебачення",
        };

        gg.setFont(font);

        FontMetrics fm = gg.getFontMetrics(gg.getFont());
        for (int i = 0; i < words.length; i++) {
            Rectangle2D stringBounds = fm.getStringBounds(words[i], gg);

            int w = fm.stringWidth(words[i]);
            int h = fm.getHeight();

            int horizontalIndent = 20;
            int verticalStep = (int) (h * 1.4);
            int x = chartWidth + horizontalIndent;
            int y = verticalStep * i - (int) stringBounds.getY();

            int rx = x - horizontalIndent / 2;
            int ry = y + (int) stringBounds.getY();
            int rw = w + horizontalIndent;
            int rh = h;

            gg.setColor(COLORS[i + 1]);
            gg.fillRoundRect(rx, ry, rw, rh, 6, 6);


            if (indexMove == i) {
                gg.setStroke(new BasicStroke(6));
            } else {
                gg.setStroke(new BasicStroke(0.5f));
                gg.setColor(Color.DARK_GRAY);
            }
            gg.drawRoundRect(rx, ry, rw, rh, 6, 6);

            rects[i] = new Rectangle(leftIndent + rx, topIndent + ry + rh - (int) stringBounds.getCenterY(), rw, rh);



            if (indexMove == i) {
                gg.setColor(color);
            } else {
                gg.setColor(Color.BLACK);
            }
            //gg.setColor(Color.BLACK);
            gg.drawString(words[i], x, y);


        }

//        for (int i = 0; i < words.length; i++) {
//            FontMetrics fm = gg.getFontMetrics(gg.getFont());
//
//            Rectangle2D rect = fm.getStringBounds(words[i], gg);
//
//            int w = (int) fm.stringWidth(words[i]);
//            int h = (int) fm.getHeight();
//
//            int x = chartWidth;
//            int y = 20 * (words.length - i);
//
//
//            rects[i] = new Rectangle(leftIndent + x, topIndent + y, w, h);
//
//
//            gg.setColor(COLORS[i + 1]);
//            gg.fillRect(chartWidth + (int) rect.getX(), 20 * (words.length - 1 - i) + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//
//            if (indexMove == i) {
//                gg.setStroke(new BasicStroke(2));
//                gg.setColor(Color.ORANGE);
//            } else {
//                gg.setStroke(new BasicStroke(1));
//                gg.setColor(Color.DARK_GRAY);
//            }
////            gg.setColor(COLORS[i + 1]);
//            gg.drawRect(chartWidth + (int) rect.getX(), 20 * (words.length - 1 - i) + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//
//            if (indexMove == i) {
//                gg.setColor(color);
//            } else {
//                gg.setColor(Color.BLACK);
//            }
//            gg.drawString(words[i], chartWidth, 20 * (words.length - 1 - i));
//
//        }







//        rect = gg.getFontMetrics(getFont()).getStringBounds(word, gg);
//        gg.setColor(COLORS[1]);
//        gg.fillRect(chartWidth + 20 + (int) rect.getX(), 100 + 20 * 8 + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//        gg.setColor(Color.WHITE);
//        gg.drawString(word, chartWidth + 20, 100 + 20 * 8);
//
//        rect = gg.getFontMetrics(getFont()).getStringBounds(word, gg);
//        gg.setColor(COLORS[2]);
//        gg.fillRect(chartWidth + 20 + (int) rect.getX(), 100 + 20 * 7 + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//        gg.setColor(Color.WHITE);
//        gg.drawString(word, chartWidth + 20, 100 + 20 * 7);
//
//        rect = gg.getFontMetrics(getFont()).getStringBounds(word, gg);
//        gg.setColor(COLORS[3]);
//        gg.fillRect(chartWidth + 20 + (int) rect.getX(), 100 + 20 * 6 + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//        gg.setColor(Color.WHITE);
//        gg.drawString(word, chartWidth + 20, 100 + 20 * 6);
//
//        rect = gg.getFontMetrics(getFont()).getStringBounds(word, gg);
//        gg.setColor(COLORS[4]);
//        gg.fillRect(chartWidth + 20 + (int) rect.getX(), 100 + 20 * 5 + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//        gg.setColor(Color.WHITE);
//        gg.drawString(word, chartWidth + 20, 100 + 20 * 5);
//
//        rect = gg.getFontMetrics(getFont()).getStringBounds(word, gg);
//        gg.setColor(COLORS[5]);
//        gg.fillRect(chartWidth + 20 + (int) rect.getX(), 100 + 20 * 4 + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//        gg.setColor(Color.WHITE);
//        gg.drawString(word, chartWidth + 20, 100 + 20 * 4);
//
//        rect = gg.getFontMetrics(getFont()).getStringBounds(word, gg);
//        gg.setColor(COLORS[6]);
//        gg.fillRect(chartWidth + 20 + (int) rect.getX(), 100 + 20 * 3 + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//        gg.setColor(Color.WHITE);
//        gg.drawString(word, chartWidth + 20, 100 + 20 * 3);
//
//        rect = gg.getFontMetrics(getFont()).getStringBounds(word, gg);
//        gg.setColor(COLORS[7]);
//        gg.fillRect(chartWidth + 20 + (int) rect.getX(), 100 + 20 * 2 + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//        gg.setColor(Color.WHITE);
//        gg.drawString(word, chartWidth + 20, 100 + 20 * 2);
//
//        rect = gg.getFontMetrics(getFont()).getStringBounds(word, gg);
//        gg.setColor(COLORS[8]);
//        gg.fillRect(chartWidth + 20 + (int) rect.getX(), 100 + 20 * 1 + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//        gg.setColor(Color.WHITE);
//        gg.drawString(word, chartWidth + 20, 100 + 20 * 1);
//
//        rect = gg.getFontMetrics(getFont()).getStringBounds(word, gg);
//        gg.setColor(COLORS[9]);
//        gg.fillRect(chartWidth + 20 + (int) rect.getX(), 100 + 20 * 0 + (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
//        gg.setColor(Color.WHITE);
//        gg.drawString(word, chartWidth + 20, 100 + 20 * 0);


//        gg.setStroke(new BasicStroke(oneMonthWidth / 2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
//        for (int i = 0; i < prices.length; i++) {
//
//            int x = i * oneMonthWidth + oneMonthWidth / 2;
//            int cy1 = chartHeight;
//
//            for (int j = 0; j < prices[0].length; j++) {
//
//                gg.setColor(COLORS[j]);
//
//                double price = prices[i][j];
//                int cy2 = (int) (cy1 - Math.ceil(one$ * price));
//
//                gg.drawLine(x, cy1, x, cy2);
//                cy1 = cy2;
//
//            }
//        }


//        for (int i = 0; i < prices.length; i++) {
//
//            int cy1 = chartHeight;
//            gg.setColor(COLORS[0]);
//            int x = i * oneMonthWidth + oneMonthWidth / 2;
//            double price = prices[i][0];
//            int cy2 = (int) (cy1 - Math.ceil(one$ * price));
//            gg.drawLine(x, cy1, x, cy2);
//
//            cy1 = cy2;
//            gg.setColor(COLORS[1]);
//            x = i * oneMonthWidth + oneMonthWidth / 2;
//            price = prices[i][1];
//            cy2 = (int) (cy1 - Math.ceil(one$ * price));
//            gg.drawLine(x, cy1, x, cy2);
//
//            cy1 = cy2;
//            gg.setColor(COLORS[2]);
//            x = i * oneMonthWidth + oneMonthWidth / 2;
//            price = prices[i][2];
//            cy2 = (int) (cy1 - Math.ceil(one$ * price));
//            gg.drawLine(x, cy1, x, cy2);
//
//            cy1 = cy2;
//            gg.setColor(COLORS[3]);
//            x = i * oneMonthWidth + oneMonthWidth / 2;
//            price = prices[i][3];
//            cy2 = (int) (cy1 - Math.ceil(one$ * price));
//            gg.drawLine(x, cy1, x, cy2);
//
//            cy1 = cy2;
//            gg.setColor(COLORS[4]);
//            x = i * oneMonthWidth + oneMonthWidth / 2;
//            price = prices[i][4];
//            cy2 = (int) (cy1 - Math.ceil(one$ * price));
//            gg.drawLine(x, cy1, x, cy2);
//
//            cy1 = cy2;
//            gg.setColor(COLORS[5]);
//            x = i * oneMonthWidth + oneMonthWidth / 2;
//            price = prices[i][5];
//            cy2 = (int) (cy1 - Math.ceil(one$ * price));
//            gg.drawLine(x, cy1, x, cy2);
//
//            cy1 = cy2;
//            gg.setColor(COLORS[6]);
//            x = i * oneMonthWidth + oneMonthWidth / 2;
//            price = prices[i][6];
//            cy2 = (int) (cy1 - Math.ceil(one$ * price));
//            gg.drawLine(x, cy1, x, cy2);
//
//            cy1 = cy2;
//            gg.setColor(COLORS[7]);
//            x = i * oneMonthWidth + oneMonthWidth / 2;
//            price = prices[i][7];
//            cy2 = (int) (cy1 - Math.ceil(one$ * price));
//            gg.drawLine(x, cy1, x, cy2);
//
//            cy1 = cy2;
//            gg.setColor(COLORS[8]);
//            x = i * oneMonthWidth + oneMonthWidth / 2;
//            price = prices[i][8];
//            cy2 = (int) (cy1 - Math.ceil(one$ * price));
//            gg.drawLine(x, cy1, x, cy2);
//
//            cy1 = cy2;
//            gg.setColor(COLORS[9]);
//            x = i * oneMonthWidth + oneMonthWidth / 2;
//            price = prices[i][9];
//            cy2 = (int) (cy1 - Math.ceil(one$ * price));
//            gg.drawLine(x, cy1, x, cy2);
//
//
//
//
////            gg.setColor(COLORS[1]);
////            x = i * oneMonthWidth + oneMonthWidth / 2;
////            price = prices[0][i];
////            cy2 = (int) (cy1 - Math.ceil(one$ * price));
////            gg.drawLine(x, cy1, x, cy2);
////
////            cy1 = cy2;
////            gg.setColor(COLORS[2]);
////            x = i * oneMonthWidth + oneMonthWidth / 2;
////            price = prices[0][i];
////            cy2 = (int) (cy1 - Math.ceil(one$ * price));
////            gg.drawLine(x, cy1, x, cy2);
//
//
//
//
////            for (int j = 0; j < prices.length; j++) {
////
////                int x = j * oneMonthWidth + oneMonthWidth / 2;
////
////                double price = prices[j][i];
////                int cy2 = (int) (cy1 - Math.ceil(one$ * price));
////
////                gg.drawLine(x, cy1, x, cy2);
////                cy1 = cy2;
////            }
//        }





/*
        Color prevColor = gg.getColor();
        Stroke prevStroke = gg.getStroke();
        gg.setStroke(new BasicStroke(oneMonthWidth / 2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));

        for (int i = 0; i < this.stats.size(); i++) {

            gg.setColor(COLORS[0]);
            int currentx = i * oneMonthWidth + oneMonthWidth / 2;
            double price = this.stats.get(i).getElectricity().getPrice();
            int currenty1 = chartHeight;
            int currenty2 = (int) (currenty1 - Math.ceil(one$ * price));
            gg.drawLine(currentx, currenty1, currentx, currenty2);

//            gg.rotate(-rightAngle);
//            String word = this.stats.get(i).getMonth() + " " + this.stats.get(i).getYear();
//            Rectangle2D rr = gg.getFontMetrics(getFont()).getStringBounds(word, gg);
//            gg.drawString(word, -(chartHeight + (int) rr.getWidth() + 2), currentx + (int) (rr.getHeight() / 2));
//            gg.rotate(rightAngle);

            gg.setColor(COLORS[1]);
            currentx = i * oneMonthWidth + oneMonthWidth / 2;
            price = this.stats.get(i).getRent();
            currenty1 = currenty2;
            currenty2 = (int) (currenty1 - Math.ceil(one$ * price));
            gg.drawLine(currentx, currenty1, currentx, currenty2);


            gg.setColor(COLORS[2]);
            currentx = i * oneMonthWidth + oneMonthWidth / 2;
            price = this.stats.get(i).getHeating();
            currenty1 = currenty2;
            currenty2 = (int) (currenty1 - Math.ceil(one$ * price));
            gg.drawLine(currentx, currenty1, currentx, currenty2);


            gg.setColor(COLORS[3]);
            currentx = i * oneMonthWidth + oneMonthWidth / 2;
            price = this.stats.get(i).getHotWater();
            currenty1 = currenty2;
            currenty2 = (int) (currenty1 - Math.ceil(one$ * price));
            gg.drawLine(currentx, currenty1, currentx, currenty2);


            gg.setColor(COLORS[4]);
            currentx = i * oneMonthWidth + oneMonthWidth / 2;
            price = this.stats.get(i).getColdWater();
            currenty1 = currenty2;
            currenty2 = (int) (currenty1 - Math.ceil(one$ * price));
            gg.drawLine(currentx, currenty1, currentx, currenty2);


            gg.setColor(COLORS[5]);
            currentx = i * oneMonthWidth + oneMonthWidth / 2;
            price = this.stats.get(i).getSewerage();
            currenty1 = currenty2;
            currenty2 = (int) (currenty1 - Math.ceil(one$ * price));
            gg.drawLine(currentx, currenty1, currentx, currenty2);


            gg.setColor(COLORS[6]);
            currentx = i * oneMonthWidth + oneMonthWidth / 2;
            price = this.stats.get(i).getGas().getPrice();
            currenty1 = currenty2;
            currenty2 = (int) (currenty1 - Math.ceil(one$ * price));
            gg.drawLine(currentx, currenty1, currentx, currenty2);


            gg.setColor(COLORS[7]);
            currentx = i * oneMonthWidth + oneMonthWidth / 2;
            price = this.stats.get(i).getGarbage();
            currenty1 = currenty2;
            currenty2 = (int) (currenty1 - Math.ceil(one$ * price));
            gg.drawLine(currentx, currenty1, currentx, currenty2);


            gg.setColor(COLORS[8]);
            currentx = i * oneMonthWidth + oneMonthWidth / 2;
            price = this.stats.get(i).getIntercom();
            currenty1 = currenty2;
            currenty2 = (int) (currenty1 - Math.ceil(one$ * price));
            gg.drawLine(currentx, currenty1, currentx, currenty2);


            gg.setColor(COLORS[9]);
            currentx = i * oneMonthWidth + oneMonthWidth / 2;
            price = this.stats.get(i).getTv();
            currenty1 = currenty2;
            currenty2 = (int) (currenty1 - Math.ceil(one$ * price));
            gg.drawLine(currentx, currenty1, currentx, currenty2);
        }
        gg.setColor(prevColor);
        gg.setStroke(prevStroke);
*/

//        gg.drawLine(originx, originy, originx + chartWidth, originy + chartHeight);


    }


}