package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_IN_DATA_POSITION = 0;
    private static final int NAME_IN_REPORT_POSITION = 0;
    private static final int SALARY_RATE_PER_HOUR_POSITION = 3;
    private static final int TOTAL_SALARY_IN_REPORT_POSITION = 2;
    private static final int WORKED_HOURS_POSITION = 2;
    private static final int ZERO_VALUE = 0;
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
        String[] report = new String[names.length];
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            report[i] = stringBuilder.append(names[i]).append(INSERT_BETWEEN_VALUES)
                                     .append(ZERO_VALUE).toString();
            stringBuilder.setLength(ZERO_VALUE);
        }
        for (int i = 0; i < names.length; i++) {
            for (String line : data) {
                String[] fieldsOfData = line.split(SPACE_DELIMITER);
                LocalDate date =
                        LocalDate.parse(fieldsOfData[DATE_IN_DATA_POSITION], formatter);
                if (line.contains(names[i]) && (date.isAfter(startOfPeriod)
                        && date.isBefore(endOfPeriod))) {
                    int workedHours = Integer.parseInt(fieldsOfData[WORKED_HOURS_POSITION]);
                    int salaryRate =
                            Integer.parseInt(fieldsOfData[SALARY_RATE_PER_HOUR_POSITION]);
                    for (int j = 0; j < report.length; j++) {
                        if (report[j].contains(names[i])) {
                            String[] fieldsOfReport = report[i].split(SPACE_DELIMITER);
                            int totalSalaryPerPeriod = Integer.parseInt(
                                    fieldsOfReport[TOTAL_SALARY_IN_REPORT_POSITION]);
                            totalSalaryPerPeriod += workedHours * salaryRate;
                            report[j] =
                                    stringBuilder.append(fieldsOfReport[NAME_IN_REPORT_POSITION])
                                                     .append(INSERT_BETWEEN_VALUES)
                                                     .append(totalSalaryPerPeriod)
                                                     .toString();
                            stringBuilder.setLength(ZERO_VALUE);
                        }
                    }
                }
            }
        }
        stringBuilder.append(TITLE_OF_REPORT + SPACE_DELIMITER).append(dateFrom)
                     .append(INSERT_BETWEEN_VALUES).append(dateTo).append(NEW_STRING_DELIMITER);
        for (int i = 0; i < report.length; i++) {
            stringBuilder.append(report[i]);
            if (i < report.length - 1) {
                stringBuilder.append(NEW_STRING_DELIMITER);
            }
        }
        return stringBuilder.toString();
    }
}
