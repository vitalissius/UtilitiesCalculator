package utilitiescalculator.statistics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilitiescalculator.util.Utils;

public class StatisticsReadWriter {

    private static List<Statistics> statistics;
    private static boolean isWrited;

    private static final String RGX_FILE_LINE_FORMAT = "\\{" +
                "\"timestamp\":([0-9]*)," +
                "\"month\":(\\b0?[1-9]{1}\\b|\\b1[0-2]{1}\\b)," +
                "\"year\":([0-9]{4})," +
                "\"electricity\":\\{\"kwh\":([0-9]*),\"price\":([0-9]*\\.?[0-9]*)}," +
                "\"rent\":([0-9]*\\.?[0-9]*)," +
                "\"heating\":([0-9]*\\.?[0-9]*)," +
                "\"hotWater\":([0-9]*\\.?[0-9]*)," +
                "\"coldWater\":([0-9]*\\.?[0-9]*)," +
                "\"sewerage\":([0-9]*\\.?[0-9]*)," +
                "\"gas\":\\{\"meterCubic\":([0-9]*),\"price\":([0-9]*\\.?[0-9]*)}," +
                "\"garbage\":([0-9]*\\.?[0-9]*)," +
                "\"intercom\":([0-9]*\\.?[0-9]*)," +
                "\"tv\":([0-9]*\\.?[0-9]*)}";

    public synchronized void write(Path statisticsFilePath, Statistics statistics) {
        StandardOpenOption[] options = { StandardOpenOption.CREATE, StandardOpenOption.APPEND };

        try (BufferedWriter writer = Files.newBufferedWriter(statisticsFilePath, options)) {

            writer.write(statistics.toString());
            writer.write(System.lineSeparator());
            writer.flush();
            isWrited = true;

        } catch (IOException e) {
            Utils.showExceptionDialog(null, e, "StatisticsReadWriter:write()");
        }
    }

    public synchronized List<Statistics> read(final Path statisticsFilePath) {
        if (statistics == null || isWrited) {
            isWrited = false;

            statistics = new ArrayList<>();

            Pattern pattern = Pattern.compile(RGX_FILE_LINE_FORMAT);

            try (BufferedReader reader = Files.newBufferedReader(statisticsFilePath)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {

                        long timestamp = Long.parseLong(matcher.group(1));
                        String month = matcher.group(2);
                        String year = matcher.group(3);
                        int electricity_kwh = Integer.parseInt(matcher.group(4));
                        double electricity_price = Double.parseDouble(matcher.group(5));
                        double rent = Double.parseDouble(matcher.group(6));
                        double heating = Double.parseDouble(matcher.group(7));
                        double hotWater = Double.parseDouble(matcher.group(8));
                        double coldWater = Double.parseDouble(matcher.group(9));
                        double sewerage = Double.parseDouble(matcher.group(10));
                        int gas_meterCubic = Integer.parseInt(matcher.group(11));
                        double gas_price = Double.parseDouble(matcher.group(12));
                        double garbage = Double.parseDouble(matcher.group(13));
                        double intercom = Double.parseDouble(matcher.group(14));
                        double tv = Double.parseDouble(matcher.group(15));

                        Statistics stat = new Statistics.Builder()
                                .timestamp(timestamp)
                                .month(month)
                                .year(year)
                                .electricity(electricity_kwh, electricity_price)
                                .rent(rent)
                                .heating(heating)
                                .hotWater(hotWater)
                                .coldWater(coldWater)
                                .sewerage(sewerage)
                                .gas(gas_meterCubic, gas_price)
                                .garbage(garbage)
                                .intercom(intercom)
                                .tv(tv)
                                .build();

                        statistics.add(stat);
                    }
                }
            } catch (IOException e) {
                Utils.showExceptionDialog(null, e, "StatisticsReadWriter:read()");
            }
        }
        return statistics;
    }

}
