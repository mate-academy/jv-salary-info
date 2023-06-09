package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int INDEX_DATE = 0;
    static final int INDEX_NAME = 1;

    static final int INDEX_HOUR = 2;
    static final int INDEX_SALARY = 3;
    static final String SEPARATOR = " ";
    private LocalDate fromDate;
    private LocalDate toDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        dateRangeValidator(dateFrom, dateTo);
        StringBuilder reportString = new StringBuilder("Report for period ");
        reportString.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int salary = 0;
            for (String employeeData : data) {
                String[] employee = employeeData.split(SEPARATOR);
                if (name.equals(employee[INDEX_NAME])) {
                    if (isWithinRange(employee[INDEX_DATE])) {
                        salary += Integer.parseInt(employee[INDEX_HOUR])
                                * Integer.parseInt(employee[INDEX_SALARY]);
                    }
                }
            }
            reportString.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        reportString.deleteCharAt(reportString.length() - 1);
        return reportString.toString();
    }

    public void dateRangeValidator(String fromDate, String toDate) {
        this.fromDate = LocalDate.parse(fromDate, formatter);
        this.toDate = LocalDate.parse(toDate, formatter);

    }

    public boolean isWithinRange(String employeeJobDate) {
        LocalDate dates = LocalDate.parse(employeeJobDate, formatter);

        if ((dates.isEqual(fromDate) || dates.isEqual(toDate))
                || (dates.isAfter(fromDate) && dates.isBefore(toDate))) {
            return true;
        }
        return false;
    }
}
