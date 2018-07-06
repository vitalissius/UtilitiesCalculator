package utilitiescalculator.gui.chart;

import java.awt.Point;
import java.util.List;
import utilitiescalculator.statistics.Statistics;

public class ColumnStackedChart {

    private static final int YEAR_AND_ONE_MONTH = 13;

    private final List<Statistics> statistics;

    private final int monthsCount;
    private final int paymentsCount = Statistics.PAYMENTS_COUNT;
    private final int priceLevelsCount = paymentsCount + 1; // 10 price's levels plus one zero level (bottom level)
    private final int paymentCeiling;

    private final int topIndent;
    private final int rightIndent;
    private final int bottomIndent;
    private final int leftIndent;

    private final double[][] priceLevels;

    public ColumnStackedChart(List<Statistics> statistics) {
        this(statistics, 0, 0, 0, 0);
    }

    public ColumnStackedChart(List<Statistics> statistics,
            int topIndent, int rightIndent, int bottomIndent, int leftIndent) {
        if (statistics.size() > YEAR_AND_ONE_MONTH) {
            this.statistics = statistics.subList(statistics.size() - YEAR_AND_ONE_MONTH, statistics.size());
        } else {
            this.statistics = statistics;
        }

        monthsCount = computeMonthsCount();
        priceLevels = computePriceLevels();
        paymentCeiling = computePaymentCeiling();

        this.topIndent = topIndent;
        this.rightIndent = rightIndent;
        this.bottomIndent = bottomIndent;
        this.leftIndent = leftIndent;


    }

    private int computeMonthsCount() {
        return this.statistics.size();
    }

    private double[][] computePriceLevels() {
        double[][] levels = new double[monthsCount][priceLevelsCount];

        for (int i = 0; i < monthsCount; i++) {
            Statistics stats = this.statistics.get(i);
            levels[i][0] = 0;                                      // bottom price level
            levels[i][1] = stats.getElectricity().getPrice();
            levels[i][2] = stats.getRent();
            levels[i][3] = stats.getHeating();
            levels[i][4] = stats.getHotWater();
            levels[i][5] = stats.getColdWater();
            levels[i][6] = stats.getSewerage();
            levels[i][7] = stats.getGas().getPrice();
            levels[i][8] = stats.getGarbage();
            levels[i][9] = stats.getIntercom();
            levels[i][10] = stats.getTv();
        }

        return levels;
    }

    private int computePaymentCeiling() {
        double maxColumnSum = Double.MIN_VALUE;

        for (Statistics stats : statistics) {
            double columnSum =
                    stats.getElectricity().getPrice() +
                    stats.getRent() +
                    stats.getHeating() +
                    stats.getHotWater() +
                    stats.getColdWater() +
                    stats.getSewerage() +
                    stats.getGas().getPrice() +
                    stats.getGarbage() +
                    stats.getIntercom() +
                    stats.getTv();

            if (columnSum > maxColumnSum) {
                maxColumnSum = columnSum;
            }
        }

        int alignmentGap = (int) (maxColumnSum / 10);
        double alignmentValue = alignmentGap - (maxColumnSum % alignmentGap);

        return (int) (maxColumnSum + alignmentValue);
    }



    public int getPaymentCeiling() {
        return paymentCeiling;
    }

    public int getMonthsCount() {
        return monthsCount;
    }

    private int chartWidth;
    private int chartHeight;
    private int monthWidth;
    private double oneBillHeight;

    private Point[][] pricePoints;

    public void updateAttributesByWidthAndHeight(int width, int height) {
        chartWidth = ((width - (leftIndent + rightIndent)) / monthsCount) * monthsCount;
        chartHeight = (int) (((double) (height - (topIndent + bottomIndent)) / paymentCeiling) * paymentCeiling);
        monthWidth = chartWidth / monthsCount;
        oneBillHeight = ((double) chartHeight) / paymentCeiling;

        pricePoints = new Point[priceLevels.length][priceLevels[0].length];
        for (int i = 0; i < priceLevels.length; i++) {
            int x = i * monthWidth + monthWidth / 2;
            int y1 = chartHeight;
            for (int j = 0; j < priceLevels[0].length; j++) {
                int y2 = (int) (y1 - Math.ceil(priceLevels[i][j] * oneBillHeight));
                pricePoints[i][j] = new Point(x, y2);
                y1 = y2;
            }
        }
    }

    public Point[][] getPricePoints() {
        return pricePoints;
    }

    public int getChartHeight() {
        return chartHeight;
    }

    public int getChartWidth() {
        return chartWidth;
    }

    public int getMonthWidth() {
        return monthWidth;
    }

    public double getOneBillHeight() {
        return oneBillHeight;
    }

    private String[] paymentNames;

    public void setPaymentNames(String[] paymentNames) {
        if (paymentNames.length != paymentsCount) {
            throw new IllegalArgumentException("Array's length of the payment names must be " + paymentsCount);
        }
        this.paymentNames = paymentNames;
    }

    public String[] getPaymentNames() {
        if (paymentNames == null) {
            paymentNames = new String[paymentsCount];
            for (int i = 0; i < paymentNames.length; i++) {
                paymentNames[i] = "";
            }
        }
        return paymentNames;
    }

    public int getPaymentsCount() {
        return paymentsCount;
    }

    public int getTopIndent() {
        return topIndent;
    }

    public int getRightIndent() {
        return rightIndent;
    }

    public int getBottomIndent() {
        return bottomIndent;
    }

    public int getLeftIndent() {
        return leftIndent;
    }

}
