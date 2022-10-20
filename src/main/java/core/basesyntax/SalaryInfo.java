package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int employeeSalary = 0;
            for (String record : data) {
                String[] parcedRecord = record.split(" ");
                if (name.equals(parcedRecord[NAME_INDEX])
                        && isDateBetween(parcedRecord[DATE_INDEX], dateFrom, dateTo)) {
                    employeeSalary += Integer.parseInt(parcedRecord[WORKING_HOURS_INDEX])
                            * Integer.parseInt(parcedRecord[INCOME_PER_HOUR_INDEX]);
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(employeeSalary);
        }
        return stringBuilder.toString();
    }

    private boolean isDateBetween(String salaryDate, String dateFrom, String dateTo) {
        LocalDate salaryDateFormatted = LocalDate.parse(salaryDate, DATE_FORMATTER);
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, DATE_FORMATTER);
        return salaryDateFormatted.isAfter(dateFromFormatted)
                && salaryDateFormatted.isBefore(dateToFormatted)
                || salaryDate.equals(dateFrom)
                || salaryDate.equals(dateTo);
    }
}
