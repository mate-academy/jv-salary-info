package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_OF_WORK_INDEX = 2;
    public static final int SALARY_FOR_AN_HOUR_INDEX = 3;
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dateFirst = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateLast = LocalDate.parse(dateTo, FORMATTER);
        LocalDate currentDate;

        StringBuilder totalSalary =
                new StringBuilder(String.format("Report for period %s - %s", dateFrom, dateTo));

        for (String name : names) {
            int employeeTotalSalary = 0;
            totalSalary.append("\n").append(name).append(" - ");

            for (String info : data) {
                String[] splittedDate = info.split(" ");

                if (name.equals(splittedDate[NAME_INDEX])) {
                    currentDate = LocalDate.parse(splittedDate[DATE_INDEX], FORMATTER);

                    if (dateFirst.compareTo(currentDate) <= 0
                            && dateLast.compareTo(currentDate) >= 0) {
                        employeeTotalSalary += Integer.parseInt(splittedDate[HOURS_OF_WORK_INDEX])
                                * Integer.parseInt(splittedDate[SALARY_FOR_AN_HOUR_INDEX]);
                    }
                }
            }
            totalSalary.append(employeeTotalSalary);
        }
        return totalSalary.toString();
    }

}
