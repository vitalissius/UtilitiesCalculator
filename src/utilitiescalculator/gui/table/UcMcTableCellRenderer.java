package utilitiescalculator.gui.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class UcMcTableCellRenderer extends DefaultTableCellRenderer {

    private static final Color BACKGROUND_COLOR_RED = new Color(255, 128, 128);
    private static final Color BACKGROUND_COLOR_WHITE = Color.WHITE;
    private final int gasBoundary;

    public UcMcTableCellRenderer() {
        this(30);
    }

    public UcMcTableCellRenderer(int gasBoundary) {
        this.gasBoundary = gasBoundary;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!isSelected) {
            Integer iValue = (Integer) value;
            if (gasBoundary != 0) {
                if (iValue > gasBoundary) {
                    setBackground(BACKGROUND_COLOR_RED);
                } else {
                    setBackground(BACKGROUND_COLOR_WHITE);
                }
            }
        }
        setHorizontalAlignment(SwingConstants.RIGHT);
        return this;
    }
}
