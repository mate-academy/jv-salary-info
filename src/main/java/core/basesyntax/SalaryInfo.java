package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPACE_SPLITTER = " ";
    private static final int DATE_COLUMN = 0;
    private static final int EMPLOYEE_COLUMN = 1;
    private static final int HOUR_COLUMN = 2;
    private static final int SALARY_COLUMN = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        String[] rowDataElements;
        int salary;
        LocalDate fromAsDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toAsDate = LocalDate.parse(dateTo, formatter);
        result.append(String.format("Report for period %s - %s", dateFrom, dateTo));
        for (String name : names) {
            salary = 0;
            for (String rowData : data) {
                rowDataElements = rowData.split(SPACE_SPLITTER);
                boolean isDateSuitable
                        = LocalDate.parse(rowDataElements[DATE_COLUMN],
                        formatter).isAfter(fromAsDate)
                        && LocalDate.parse(rowDataElements[DATE_COLUMN],
                        formatter).isBefore(toAsDate)
                        || LocalDate.parse(rowDataElements[DATE_COLUMN],
                        formatter).isEqual(fromAsDate)
                        || LocalDate.parse(rowDataElements[DATE_COLUMN],
                        formatter).isEqual(toAsDate);
                if (!name.isEmpty() && name.equals(rowDataElements[EMPLOYEE_COLUMN])
                        && isDateSuitable) {
                    salary += Integer.parseInt(rowDataElements[HOUR_COLUMN])
                            * Integer.parseInt(rowDataElements[SALARY_COLUMN]);
                }
            }
            result.append(System.lineSeparator()).append(String.format("%s - %d", name, salary));
        }
        return result.toString();
    }
}
