package utilitiescalculator.gui.table;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import utilitiescalculator.Dictionary;
import utilitiescalculator.Settings;
import utilitiescalculator.statistics.Statistics;
import utilitiescalculator.statistics.StatisticsReadWriter;

public class UcStatisticsTableModel extends AbstractTableModel {

    public enum ColumnKind {
        Timestamp,
        YearMonth,
        Electricity,
        Rent,
        Heating,
        HotWater,
        ColdWater,
        Sewerage,
        Gas,
        Garbage,
        Intercom,
        Tv,
        Kwh,
        Mc;

        public String columnName() {
            int firstTcKeywordOrdinal = Dictionary.Keyword.TC_TIMESTAMP.ordinal();
            int thisOrdinal = ordinal();
            int neededTcKeywordOrinal = firstTcKeywordOrdinal + thisOrdinal;
            Dictionary.Keyword neededTcKeyword = Dictionary.Keyword.values()[neededTcKeywordOrinal];
            return Dictionary.INSTANCE.getWord(neededTcKeyword);//Dictionary.Keyword.values()[Dictionary.Keyword.TC_TIMESTAMP.ordinal() + ordinal()]
        }

        public static ColumnKind columnKindByColumnName(String columnName) {
            for (ColumnKind columnKind : values()) {
                if (columnKind.columnName().equals(columnName)) {
                    return columnKind;
                }
            }
            throw new IllegalArgumentException("There is no " + columnName);
        }

        public boolean isChecked() {
            return FLAGS.get(this);
        }
    }

    private static final Map<ColumnKind, Boolean> FLAGS = new LinkedHashMap<ColumnKind, Boolean>(){{
        put(ColumnKind.Timestamp, Boolean.FALSE);
        put(ColumnKind.YearMonth, Boolean.FALSE);
        put(ColumnKind.Electricity, Boolean.FALSE);
        put(ColumnKind.Rent, Boolean.FALSE);
        put(ColumnKind.Heating, Boolean.FALSE);
        put(ColumnKind.HotWater, Boolean.FALSE);
        put(ColumnKind.ColdWater, Boolean.FALSE);
        put(ColumnKind.Sewerage, Boolean.FALSE);
        put(ColumnKind.Gas, Boolean.FALSE);
        put(ColumnKind.Garbage, Boolean.FALSE);
        put(ColumnKind.Intercom, Boolean.FALSE);
        put(ColumnKind.Tv, Boolean.FALSE);
        put(ColumnKind.Kwh, Boolean.FALSE);
        put(ColumnKind.Mc, Boolean.FALSE);
    }};

    private static final Class<?>[] COLUMN_CLASSES = {
        Long.class, YearMonth.class,
        Double.class, Double.class, Double.class, Double.class, Double.class,
        Double.class, Double.class, Double.class, Double.class, Double.class,
        Integer.class, Integer.class,
    };


    private List<Statistics> statistics;
    private List<ColumnKind> shownColumns = new ArrayList<>();

    public UcStatisticsTableModel(List<Boolean> flags) {
        statistics = new StatisticsReadWriter().read(Settings.UC_STATISTICS_FILE_PATH);
        Collections.sort(statistics, Collections.reverseOrder((lhs, rhs) -> {
            return Long.compare(lhs.getTimestamp(), rhs.getTimestamp());
        }));

        for (ColumnKind ct : ColumnKind.values()) {
            FLAGS.put(ct, flags.get(ct.ordinal()));
        }

        for (int i = 0; i < flags.size(); i++) {
            if (flags.get(i)) {
                shownColumns.add(getColumnTypeByIndex(i));
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return COLUMN_CLASSES[shownColumns.get(columnIndex).ordinal()];
    }

    @Override
    public int getRowCount() {
        return statistics.size();
    }

    @Override
    public int getColumnCount() {
        return shownColumns.size();
    }

    @Override
    public String getColumnName(int column) {
        return ColumnKind.values()[shownColumns.get(column).ordinal()].columnName();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void checkUncheck(ColumnKind columnKind) {
        boolean isChecked = columnKind.isChecked();
        if (isChecked) {
            shownColumns.remove(columnKind);
        } else {
            int ordinal = columnKind.ordinal();

            List<Boolean> subList = new ArrayList<>(FLAGS.values()).subList(0, ordinal);
            long columnAmountBeforeParamColumn = subList.stream().filter((f) -> f.equals(Boolean.TRUE)).count();
            int index = (int) columnAmountBeforeParamColumn;

            shownColumns.add(index, columnKind);
        }
        FLAGS.put(columnKind, !isChecked);
        fireTableStructureChanged();
    }

    private ColumnKind getColumnTypeByIndex(int columnIndex) {
        return ColumnKind.values()[columnIndex];
    }

    public List<Boolean> getFlags() {
        return new ArrayList<>(FLAGS.values());
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Statistics stat = statistics.get(rowIndex);
        switch (shownColumns.get(columnIndex)) {
            case Timestamp:
                return stat.getTimestamp();
            case YearMonth:
                int year = Integer.parseInt(stat.getYear());
                int month = Integer.parseInt(stat.getMonth());
                return YearMonth.of(year, month);
            case Electricity:
                return stat.getElectricity().getPrice();
            case Rent:
                return stat.getRent();
            case Heating:
                return stat.getHeating();
            case HotWater:
                return stat.getHotWater();
            case ColdWater:
                return stat.getColdWater();
            case Sewerage:
                return stat.getSewerage();
            case Gas:
                return stat.getGas().getPrice();
            case Garbage:
                return stat.getGarbage();
            case Intercom:
                return stat.getIntercom();
            case Tv:
                return stat.getTv();
            case Kwh:
                return stat.getElectricity().getKwh();
            case Mc:
                return stat.getGas().getMeterCubic();
        }
        return new IllegalArgumentException();
    }

}
