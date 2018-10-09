package utilitiescalculator.gui.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;

public class UcTotalAmountTableCellRenderer extends UcDoubleTableCellRenderer {

    private static final Color BACKGROUND_COLOR_MARKED = new Color(255, 200, 200);  // red
    private static final Color BACKGROUND_COLOR_DEFAULT = new Color(200, 255, 200); // green

    private final double boundary;

    public UcTotalAmountTableCellRenderer() {
        boundary = 0;
    }

    public UcTotalAmountTableCellRenderer(double boundary) {
        this.boundary = boundary;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!isSelected) {
            Double dValue = (Double) value;
            if (Double.compare(boundary, 0.0) != 0) {
                if (dValue > boundary) {
                    setBackground(BACKGROUND_COLOR_MARKED);
                } else {
                    setBackground(BACKGROUND_COLOR_DEFAULT);
                }
            }
        }
        return this;
    }
}
