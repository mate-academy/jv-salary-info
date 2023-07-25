package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int INDEX_OF_DATE_FROM_DATA = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            builder.append(LINE_SEPARATOR).append(names[i]).append(" - ");
            for (int j = 0; j < data.length; j++) {
                String[] splitedData = data[j].split(" ");
                LocalDate dateFromData = LocalDate.parse(splitedData[INDEX_OF_DATE_FROM_DATA],
                        DATE_TIME_FORMATTER);
                if (splitedData[INDEX_OF_NAME].equals(names[i])) {
                    if ((dateFromData.equals(fromDate) || dateFromData.isAfter(fromDate))
                            && (dateFromData.equals(toDate) || dateFromData.isBefore(toDate))) {
                        salary = salary + Integer.valueOf(splitedData[INDEX_OF_HOURS])
                                * Integer.valueOf(splitedData[INDEX_OF_SALARY_PER_HOUR]);
                    }
                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }
}
