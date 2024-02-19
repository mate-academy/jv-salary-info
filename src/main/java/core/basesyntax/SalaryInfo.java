package core.basesyntax;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_SALARY = 3;
    private static final String DASH = " - ";
    private static final String SALARY_INFO_HEADER = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = getLocalDate(dateFrom);
        LocalDate localDateTo = getLocalDate(dateTo);
        StringBuilder resultString = new StringBuilder(SALARY_INFO_HEADER)
                .append(dateFrom).append(DASH)
                .append(dateTo);

        for (String employeeName : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] row = record.split(" ");
                String dateInRow = getDateFromRow(row);
                String nameInRow = getNameFromRow(row);
                LocalDate rowDate = getLocalDate(dateInRow);
                if (employeeName.equals(nameInRow)
                        && rowDate.isAfter(localDateFrom)
                        && !rowDate.isAfter(localDateTo)) {
                    int hours = getHoursFromRow(row);
                    int salary = getSalaryFromRow(row);
                    totalSalary += (salary * hours);
                }
            }
            resultString.append(System.lineSeparator())
                    .append(employeeName).append(DASH)
                    .append(totalSalary);
        }
        return resultString.toString();
    }

    public LocalDate getLocalDate(String date) {
        try {
            return LocalDate.parse(date, FORMATTER);
        } catch (DateTimeException e) {
            throw new DateTimeException("Not valid date in: " + date);
        }
    }

    private String getNameFromRow(String[] row) {
        return row[INDEX_OF_NAME];
    }

    private String getDateFromRow(String[] row) {
        return row[INDEX_OF_DATE];
    }

    private int getSalaryFromRow(String[] row) {
        return Integer.valueOf(row[INDEX_OF_SALARY]);
    }

    private int getHoursFromRow(String[] row) {
        return Integer.valueOf(row[INDEX_OF_HOURS]);
    }

}
