package utilitiescalculator.gui.table;

import java.awt.Color;
import java.awt.Component;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import utilitiescalculator.Dictionary;

public class UcYearMonthTableCellRenderer extends DefaultTableCellRenderer {

    private static final Color BACKGROUND_COLOR_WINTER = new Color(196,233,242);
    private static final Color BACKGROUND_COLOR_SPRING = new Color(174,238,193);
    private static final Color BACKGROUND_COLOR_SUMMER = new Color(252,199,202);
    private static final Color BACKGROUND_COLOR_AUTUMN = new Color(255,231,145);
    private final Locale LOCALE =
            Locale.forLanguageTag(Dictionary.INSTANCE.getWord(Dictionary.Keyword.LANGUAGE_TAG));

    @Override
    protected void setValue(Object value) {
        YearMonth ymValue = (YearMonth) value;
        int yearNumber = ymValue.getYear();
        String monthLocalName = ymValue.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, LOCALE);
        super.setValue(yearNumber + " " + monthLocalName);
        System.out.println(LOCALE);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        YearMonth ymValue = (YearMonth) value;
        if (!isSelected) {
            switch (ymValue.getMonth()) {
                case JANUARY:
                case FEBRUARY:
                case DECEMBER:
                    setBackground(BACKGROUND_COLOR_WINTER);
                    break;
                case MARCH:
                case APRIL:
                case MAY:
                    setBackground(BACKGROUND_COLOR_SPRING);
                    break;
                case JUNE:
                case JULY:
                case AUGUST:
                    setBackground(BACKGROUND_COLOR_SUMMER);
                    break;
                case SEPTEMBER:
                case OCTOBER:
                case NOVEMBER:
                    setBackground(BACKGROUND_COLOR_AUTUMN);
                    break;
            }
        }
        return this;
    }
}
