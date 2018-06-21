package utilitiescalculator.gui.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class UcDoubleTableCellRenderer extends DefaultTableCellRenderer {

    private static final Color BACKGROUND_COLOR = new Color(242, 251, 253);
    private static final Color FOREGROUND_COLOR_GRAY = Color.GRAY;
    private static final Color FOREGROUND_COLOR_BLACK = Color.BLACK;
    private static final String FORMAT = "%.2f";

    @Override
    protected void setValue(Object value) {
        Double dValue = (Double) value;
        super.setValue(String.format(FORMAT, dValue));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!isSelected) {
            Double dValue = (Double) value;
            if (dValue.compareTo(0.0) == 0) {
                setForeground(FOREGROUND_COLOR_GRAY);
            } else {
                setForeground(FOREGROUND_COLOR_BLACK);
            }
            setBackground(BACKGROUND_COLOR);
        }
        setHorizontalAlignment(SwingConstants.RIGHT);
        return this;
    }
}
