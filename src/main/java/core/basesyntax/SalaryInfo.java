package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOUR = 2;
    private static final int INDEX_SALARY = 3;
    private static final String SEPARATOR = " ";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportString = new StringBuilder("Report for period ");
        reportString.append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String employeeData : data) {
                String[] employee = employeeData.split(SEPARATOR);
                if (name.equals(employee[INDEX_NAME])) {
                    if (isWithinRange(employee[INDEX_DATE], dateFrom, dateTo)) {
                        salary += Integer.parseInt(employee[INDEX_HOUR])
                                * Integer.parseInt(employee[INDEX_SALARY]);
                    }
                }
            }
            reportString.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportString.toString();
    }

    private boolean isWithinRange(String employeeJobDate, String fromDate, String toDate) {
        LocalDate dates = LocalDate.parse(employeeJobDate, formatter);
        return !dates.isAfter(LocalDate.parse(toDate, formatter))
                && !dates.isBefore(LocalDate.parse(fromDate, formatter));
    }
}
