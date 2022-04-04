package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom,
                                String dateTo) {
        // statistic array
        //  data | name | working-hours | salary-per-hour | salary-for-the-period
        //#  0   | 1    |       2       |         3                4
        String[][] statistic = splitDataArray(data);
        String[][] calculateSalaryForPeriod = calculateSalaryForPeriod(names,
                statistic, dateFrom, dateTo);
        return report(calculateSalaryForPeriod, dateFrom, dateTo);
    }

    public String[][] splitDataArray(String[] data) {
        String[][] info = new String[data.length][5];
        for (int i = 0; i < data.length; i++) {
            String[] split = data[i].split(" ");
            for (int j = 0; j < split.length; j++) {
                info[i][j] = split[j];
            }
        }
        return info;
    }

    private String report(String[][] info, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String[] strings : info) {
            stringBuilder.append(System.lineSeparator())
                    .append(strings[0])
                    .append(" - ")
                    .append(strings[1]);
        }
        return stringBuilder.toString();
    }

    private String[][] calculateSalaryForPeriod(String[] names, String[][] data,
                                                String dateFrom,
                                                String dateTo) {
        // statisticsForReport array
        // #  0             1
        // 0 name salary-for-the-period
        String[][] statisticsForReport = new String[names.length][2];
        for (int i = 0; i < names.length; i++) {
            statisticsForReport[i][0] = names[i];
            statisticsForReport[i][1] = "0";
        }
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate spentWorkDay;
        for (String[] datum : data) {
            spentWorkDay = LocalDate.parse(datum[0], DATE_TIME_FORMATTER);
            for (int j = 0; j < names.length; j++) {
                if (isWithinPeriod(spentWorkDay, startDate, endDate)
                        && statisticsForReport[j][0].equals(datum[1])) {
                    int incomePerHour
                            = Integer.parseInt(statisticsForReport[j][1])
                            + Integer.parseInt(datum[2])
                            * Integer.parseInt(datum[3]);
                    statisticsForReport[j][1] = Integer.toString(incomePerHour);
                }
            }
        }
        return statisticsForReport;
    }

    private static boolean isWithinPeriod(LocalDate spentWorkDay,
                                          LocalDate startDate,
                                          LocalDate endDate) {
        return (spentWorkDay.isAfter(startDate.minusDays(1))
                && spentWorkDay.isBefore(endDate.plusDays(1)));
    }
}


