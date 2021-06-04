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
        LocalDate dateInput;

        StringBuilder totalSalary = new StringBuilder("Report for period ");
        totalSalary.append(dateFrom).append(" - ").append(dateTo).append("\n");

        for (String name : names) {
            int employeeTotalSalary = 0;
            totalSalary.append(name).append(" - ");

            for (String info : data) {
                String[] record = info.split(" ");

                if (name.equals(record[NAME_INDEX])) {
                    dateInput = LocalDate.parse(record[DATE_INDEX], FORMATTER);

                    if (dateFirst.compareTo(dateInput) <= 0 && dateLast.compareTo(dateInput) >= 0) {
                        employeeTotalSalary += Integer.parseInt(record[HOURS_OF_WORK_INDEX])
                                * Integer.parseInt(record[SALARY_FOR_AN_HOUR_INDEX]);
                    }
                }
            }
            totalSalary.append(employeeTotalSalary).append("\n");
        }
        return totalSalary.toString().trim();
    }

}
