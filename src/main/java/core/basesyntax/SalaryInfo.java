package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "d.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final String DATA_SEPARATOR = " - ";
    private static final String COLUMN_SEPARATOR = " ";
    private static final String REPORT_HEADER = "Report for period ";
    private static final int DATE_COLUMN_INDEX = 0;
    private static final int NAME_COLUMN_INDEX = 1;
    private static final int QOTY_COLUMN_INDEX = 2;
    private static final int SUM_COLUMN_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder(REPORT_HEADER)
                .append(dateFrom).append(DATA_SEPARATOR).append(dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(DATA_SEPARATOR);
            builder.append(getTotalSalaryByName(data, name, dateFromLocalDate, dateToLocalDate));
        }
        return builder.toString();
    }

    private int getTotalSalaryByName(String[] data, String name,
                                     LocalDate dateFromLocalDate, LocalDate dateToLocalDate) {
        int totalSalary = 0;
        for (String dataRow : data) {
            if (dataRow.isEmpty()) {
                continue;
            }
            String[] dataRowArray = dataRow.split(COLUMN_SEPARATOR);
            LocalDate date = LocalDate.parse(dataRowArray[DATE_COLUMN_INDEX], formatter);
            if ((date.isAfter(dateFromLocalDate) && (date.isBefore(dateToLocalDate))
                    || date.equals(dateToLocalDate))
                    && dataRowArray[NAME_COLUMN_INDEX].equals(name)) {
                totalSalary += Integer.parseInt(dataRowArray[QOTY_COLUMN_INDEX])
                        * Integer.parseInt(dataRowArray[SUM_COLUMN_INDEX]);
            }
        }
        return totalSalary;
    }
}
