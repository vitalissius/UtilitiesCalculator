package utilitiescalculator.util;

import java.awt.Component;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.YearMonth;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import utilitiescalculator.Dictionary;
import utilitiescalculator.gui.table.UcDoubleTableCellRenderer;
import utilitiescalculator.gui.table.UcKwhTableCellRenderer;
import utilitiescalculator.gui.table.UcMcTableCellRenderer;
import utilitiescalculator.gui.table.UcStatisticsTableModel;
import utilitiescalculator.gui.table.UcTimestampTableCellRenderer;
import utilitiescalculator.gui.table.UcYearMonthTableCellRenderer;

public class Utils {
    public static void showExceptionDialog(Component component, Exception exception, String title) {
        String message = "";
        try (StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter)) {

            exception.printStackTrace(printWriter);
            message = stringWriter.toString();

        } catch (IOException ignored) {
        }
        JOptionPane.showMessageDialog(component, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void setStyleOfRenderingToUcStatisticsTable(JTable table) {
        table.setModel(new UcStatisticsTableModel(((UcStatisticsTableModel) table.getModel()).getFlags()));

        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            TableCellRenderer renderer = null;

            Class<?> clss = table.getColumnClass(i);
            if (clss == Double.class) {
                renderer = new UcDoubleTableCellRenderer();
            } else if (clss == Long.class) {
                renderer = new UcTimestampTableCellRenderer();
            } else if (clss == YearMonth.class) {
                renderer = new UcYearMonthTableCellRenderer();
            } else if (clss == Integer.class) {
                String columnName = table.getModel().getColumnName(i);
                if (Dictionary.INSTANCE.getWord(Dictionary.Keyword.TC_KWH).equals(columnName)) {
                    renderer = new UcKwhTableCellRenderer();
                } else if (Dictionary.INSTANCE.getWord(Dictionary.Keyword.TC_MCUBIC).equals(columnName)) {
                    renderer = new UcMcTableCellRenderer();
                }
            }

            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    public static void resetStyleOfRenderingToUcStatisticsTable(JTable table) {
        setStyleOfRenderingToUcStatisticsTable(table);
    }

}
