package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int COLUMN_WITH_DATE = 0;
    private static final int COLUMN_WITH_NAME = 1;
    private static final int COLUMN_WITH_HOURS = 2;
    private static final int COLUMN_WITH_SALARY_PER_HOUR = 3;
    private static final String DATA_SEPARATOR = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        String[] splitData;
        int salaryOfName;
        for (String name : names) {
            salaryOfName = 0;
            for (String dayData : data) {
                splitData = dayData.split(DATA_SEPARATOR);
                if (splitData[COLUMN_WITH_NAME].equals(name)) {
                    if (checkDates(splitData[COLUMN_WITH_DATE],
                            dateFromFormatted, dateToFormatted)) {
                        salaryOfName += Integer.parseInt(splitData[COLUMN_WITH_HOURS])
                                * Integer.parseInt(splitData[COLUMN_WITH_SALARY_PER_HOUR]);
                    }
                }
            }
            stringBuilder.append(name)
                    .append(" - ")
                    .append(salaryOfName)
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }

    private boolean checkDates(String currentDate, LocalDate dateFromFormatted,
                               LocalDate dateToFormatted) {
        LocalDate dataFormatted = LocalDate.parse(currentDate, FORMATTER);
        return (dataFormatted.isEqual(dateFromFormatted)
                || dataFormatted.isEqual(dateToFormatted)
                || dataFormatted.isAfter(dateFromFormatted)
                && dataFormatted.isBefore(dateToFormatted));
    }
}
