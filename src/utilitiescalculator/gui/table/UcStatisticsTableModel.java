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
        Timestamp(Boolean.FALSE),
        YearMonth(Boolean.TRUE),
        Electricity(Boolean.FALSE),
        Rent(Boolean.FALSE),
        Heating(Boolean.FALSE),
        HotWater(Boolean.FALSE),
        ColdWater(Boolean.FALSE),
        Sewerage(Boolean.FALSE),
        Gas(Boolean.FALSE),
        Garbage(Boolean.FALSE),
        Intercom(Boolean.FALSE),
        Tv(Boolean.FALSE),
        TotalAmount(Boolean.FALSE),
        Kwh(Boolean.FALSE),
        Mc(Boolean.FALSE);

        private final boolean alwaysShown;

        private ColumnKind(boolean alwaysShown) {
            this.alwaysShown = alwaysShown;
        }

        public boolean alwaysShown() {
            return alwaysShown;
        }

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
        put(ColumnKind.Timestamp, Boolean.TRUE);
        put(ColumnKind.YearMonth, Boolean.TRUE);
        put(ColumnKind.Electricity, Boolean.TRUE);
        put(ColumnKind.Rent, Boolean.TRUE);
        put(ColumnKind.Heating, Boolean.TRUE);
        put(ColumnKind.HotWater, Boolean.TRUE);
        put(ColumnKind.ColdWater, Boolean.TRUE);
        put(ColumnKind.Sewerage, Boolean.TRUE);
        put(ColumnKind.Gas, Boolean.TRUE);
        put(ColumnKind.Garbage, Boolean.TRUE);
        put(ColumnKind.Intercom, Boolean.TRUE);
        put(ColumnKind.Tv, Boolean.TRUE);
        put(ColumnKind.TotalAmount, Boolean.TRUE);
        put(ColumnKind.Kwh, Boolean.TRUE);
        put(ColumnKind.Mc, Boolean.TRUE);
    }};

    private static final Class<?>[] COLUMN_CLASSES = {
        Long.class, YearMonth.class,
        Double.class, Double.class, Double.class, Double.class, Double.class,
        Double.class, Double.class, Double.class, Double.class, Double.class,
        Double.class,
        Integer.class, Integer.class,
    };


    private List<Statistics> statistics;
    private List<ColumnKind> shownColumns = new ArrayList<>();

    private static final int ALL_SHOWN_COLUMNS_MASK =
            Integer.parseInt(new String(new char[ColumnKind.values().length]).replace('\0', '1'), 2);

    {
        statistics = new StatisticsReadWriter().read(Settings.UC_STATISTICS_FILE_PATH);
        Collections.sort(statistics, Collections.reverseOrder((lhs, rhs) -> {
            return Long.compare(lhs.getTimestamp(), rhs.getTimestamp());
        }));
    }

    private static final int ALWAYS_SHOWN_COLUMNS_MASK = alwaysShownColumnsMask();

    private static int alwaysShownColumnsMask() {
        int alwaysShownColumnsMask = 0;
        for (ColumnKind ck : ColumnKind.values()) {
            if (ck.alwaysShown()) {
                alwaysShownColumnsMask = alwaysShownColumnsMask | (1 << ck.ordinal());
            }
        }
        return alwaysShownColumnsMask;
    }

    public UcStatisticsTableModel(int shownColumnsMask) {
        shownColumnsMask = shownColumnsMask & ALL_SHOWN_COLUMNS_MASK;

        int flag = shownColumnsMask | ALWAYS_SHOWN_COLUMNS_MASK;
        if (flag >= ALWAYS_SHOWN_COLUMNS_MASK) {
            for (ColumnKind ck : ColumnKind.values()) {
                int ordinal = ck.ordinal();
                boolean isShown = ((flag & (1 << ordinal)) >>> ordinal) == 1;
                FLAGS.put(ck, isShown ? Boolean.TRUE : Boolean.FALSE);
                if (isShown) {
                    shownColumns.add(ck);
                }
            }
        } else {
            for (ColumnKind ct : ColumnKind.values()) {
                if (ct.isChecked()) {
                    shownColumns.add(ct);
                }
            }
        }
    }

    public UcStatisticsTableModel(List<Boolean> flags) {
        for (ColumnKind ct : ColumnKind.values()) {
            FLAGS.put(ct, flags.get(ct.ordinal()));
            if (ct.isChecked()) {
                shownColumns.add(ct);
            }
        }
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

    public List<Boolean> getFlags() {
        return new ArrayList<>(FLAGS.values());
    }

    public int getShownColumnsMask() {
        int shownColumnsMask = 0;
        int shift = 0;
        for (Boolean flag : FLAGS.values()) {
            if (flag) {
                shownColumnsMask = shownColumnsMask | (1 << shift);
            }
            shift++;
        }
        return shownColumnsMask;
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
            case TotalAmount:
                return stat.getElectricity().getPrice() + stat.getRent() + stat.getHeating() + stat.getHotWater() +
                        stat.getColdWater() + stat.getSewerage() + stat.getGas().getPrice() + stat.getGarbage() +
                        stat.getIntercom() + stat.getTv();
            case Kwh:
                return stat.getElectricity().getKwh();
            case Mc:
                return stat.getGas().getMeterCubic();
        }
        return new IllegalArgumentException();
    }

}
