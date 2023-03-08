package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int WORKING_HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        StringBuilder info = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String employee : names) {
            int workerSalary = 0;

            for (String elm : data) {
                String[] workerInfo = elm.split(" ");
                LocalDate workday = LocalDate.parse(workerInfo[DATE], formatter);

                if (employee.equals(workerInfo[NAME])
                        && (workday.isEqual(from) || workday.isAfter(from))
                        && (workday.isBefore(to) || workday.isEqual(to))) {
                    workerSalary += (Integer.valueOf(workerInfo[WORKING_HOURS])
                            * Integer.valueOf(workerInfo[INCOME_PER_HOUR]));
                }
            }
            info.append(System.lineSeparator()).append(employee).append(" - ").append(workerSalary);
        }

        return info.toString();
    }
}
