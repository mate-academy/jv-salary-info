package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_IN_DATA_POSITION = 0;
    private static final int SALARY_RATE_PER_HOUR_POSITION = 3;
    private static final int WORKED_HOURS_POSITION = 2;
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String MINUS_DELIMITER = "-";
    private static final String NEW_STRING_DELIMITER = "\n";
    private static final String SPACE_DELIMITER = " ";
    private static final String TITLE_OF_REPORT = "Report for period";
    private static final String INSERT_BETWEEN_VALUES = SPACE_DELIMITER + MINUS_DELIMITER
            + SPACE_DELIMITER;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate startOfPeriod = LocalDate.parse(dateFrom, formatter);
        LocalDate endOfPeriod = LocalDate.parse(dateTo, formatter);
        if (!startOfPeriod.equals(endOfPeriod)) {
            endOfPeriod = endOfPeriod.plusDays(1);
        }
        int[] totalSumsForReport = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String line : data) {
                String[] fieldsOfData = line.split(SPACE_DELIMITER);
                LocalDate date = LocalDate.parse(fieldsOfData[DATE_IN_DATA_POSITION], formatter);
                if (line.contains(names[i]) && (date.isAfter(startOfPeriod)
                        && date.isBefore(endOfPeriod))) {
                    totalSumsForReport[i] += getTotalSumPerDay(fieldsOfData);
                }
            }
        }
        return makeReport(names, totalSumsForReport, dateFrom, dateTo);
    }

    private int getTotalSumPerDay(String[] fieldsOfData) {
        int workedHours = Integer.parseInt(fieldsOfData[WORKED_HOURS_POSITION]);
        int salaryRate = Integer.parseInt(fieldsOfData[SALARY_RATE_PER_HOUR_POSITION]);
        return workedHours * salaryRate;
    }

    private String makeReport(String[] names, int[] totalSumForReport,
                              String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(makeHeaderOfReport(dateFrom, dateTo));
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(names[i]).append(INSERT_BETWEEN_VALUES)
                         .append(totalSumForReport[i]);
            if (i < names.length - 1) {
                stringBuilder.append(NEW_STRING_DELIMITER);
            }
        }
        return stringBuilder.toString();
    }

    private String makeHeaderOfReport(String dateFrom, String dateTo) {
        return TITLE_OF_REPORT + SPACE_DELIMITER + dateFrom + SPACE_DELIMITER
                + MINUS_DELIMITER + SPACE_DELIMITER + dateTo + NEW_STRING_DELIMITER;
    }
}
