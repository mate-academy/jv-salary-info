package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final String DATE_FORMAT = "d.MM.yyyy";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final String DATA_SEPARATOR = " - ";
    public static final String COLUMN_SEPARATOR = " ";
    public static final String REPORT_HEADER = "Report for period ";
    public static final int DATE_COLUMN_INDEX = 0;
    public static final int NAME_COLUMN_INDEX = 1;
    public static final int QOTY_COLUMN_INDEX = 2;
    public static final int SUM_COLUMN_INDEX = 3;

    private int getTotalSalaryByName(String[] data, String name,
                                     LocalDate dateFromLocalDate, LocalDate dateToLocalDate) {
        int totalSalary = 0;
        for (String dataRow : data) {
            if (dataRow.isEmpty()) {
                continue;
            }
            String[] dataRowArray = dataRow.split(COLUMN_SEPARATOR);
            LocalDate date;
            try {
                date = LocalDate.parse(dataRowArray[DATE_COLUMN_INDEX], formatter);
            } catch (DateTimeParseException e) {
                throw new DateTimeParseException("Error paring date in data rows",
                        e.getParsedString(), e.getErrorIndex());
            }
            if ((date.isAfter(dateFromLocalDate) || date.equals(dateFromLocalDate))
                    && (date.isBefore(dateToLocalDate) || date.equals(dateToLocalDate))
                    && dataRowArray[NAME_COLUMN_INDEX].equals(name)) {
                totalSalary += Integer.parseInt(dataRowArray[QOTY_COLUMN_INDEX])
                        * Integer.parseInt(dataRowArray[SUM_COLUMN_INDEX]);
            }
        }
        return totalSalary;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocalDate;
        LocalDate dateToLocalDate;
        try {
            dateFromLocalDate = LocalDate.parse(dateFrom, formatter);
            dateToLocalDate = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Error paring input period",
                    e.getParsedString(), e.getErrorIndex());
        }

        StringBuilder builder = new StringBuilder(REPORT_HEADER)
                .append(dateFrom).append(DATA_SEPARATOR).append(dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(DATA_SEPARATOR);
            builder.append(getTotalSalaryByName(data, name, dateFromLocalDate, dateToLocalDate));
        }
        return builder.toString();
    }
}
