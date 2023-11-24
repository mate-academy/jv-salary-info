package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOUR = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;
            for (String info : data) {
                String[] employeeInfo = info.split(" ");
                LocalDate workDate = LocalDate.parse(employeeInfo[DATE_INDEX], FORMATTER);
                if (name.equals(employeeInfo[NAME_INDEX])
                        && (workDate.isAfter(localFrom)
                        || workDate.isEqual(localFrom))
                        && (workDate.isBefore(localTo)
                        || workDate.isEqual(localTo))) {
                    salary += Integer.parseInt(employeeInfo[WORK_HOUR])
                            * Integer.parseInt(employeeInfo[INCOME_PER_HOUR]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
