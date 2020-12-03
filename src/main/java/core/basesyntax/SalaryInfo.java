package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate to = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder reportPeriod = new StringBuilder("Report for period "
                + from.format(DATE_FORMAT)
                + " - " + to.format(DATE_FORMAT));
        for (String name : names) {
            int salaryBound = 0;
            for (String information : data) {
                String[] employeeSalary = information.split(" ");
                LocalDate workDay =
                        LocalDate.parse(employeeSalary[0], DATE_FORMAT);
                if (name.equals(employeeSalary[1])
                        && (from.isBefore(workDay) || from.isEqual(workDay))
                        && (to.isAfter(workDay) || to.isEqual(workDay))) {
                    salaryBound += Integer.parseInt(employeeSalary[2])
                            * Integer.parseInt(employeeSalary[3]);
                }

            }
            reportPeriod.append("\n").append(name).append(" - ").append(salaryBound);
        }
        return reportPeriod.toString();
    }
}
