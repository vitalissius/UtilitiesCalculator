package utilitiescalculator.gui.table;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableCellRenderer;

public class UcTimestampTableCellRenderer extends DefaultTableCellRenderer {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    @Override
    protected void setValue(Object value) {
        Long lValue = (Long) value;
        Instant instant = Instant.ofEpochMilli(lValue);
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZONE_ID);
        super.setValue(ldt.format(FORMATTER));
    }
}
