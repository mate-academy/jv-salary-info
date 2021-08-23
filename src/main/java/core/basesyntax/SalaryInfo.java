package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] lineInfo = datum.split(" ");
                if (lineInfo[NAME_INDEX].equals(name)
                        && dateCheck(lineInfo[DATE_INDEX], dateFrom, dateTo)) {
                    totalSalary += Integer.parseInt(lineInfo[HOURS_INDEX])
                            * Integer.parseInt(lineInfo[SALARY_INDEX]);
                }
            }
            salaryInfo.append(name).append(" - ").append(totalSalary)
                    .append(System.lineSeparator());
        }
        return salaryInfo.toString().trim();
    }

    private boolean dateCheck(String date, String dateFrom, String dateTo) {
        LocalDate localDate = LocalDate.parse(date, DATE_FORMATTER);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        return localDate.equals(localDateFrom)
                || localDate.equals(localDateTo)
                || localDate.isAfter(localDateFrom)
                && localDate.isBefore(localDateTo);
    }
}
