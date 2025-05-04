package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryAmount = new StringBuilder();

        for (String name: names) {
            int salary = 0;
            for (String info: data) {
                String[] information = info.split(" ");
                LocalDate dateFormed = LocalDate.parse(information[DATE], FORMATTER);
                if (!dateFormed.isAfter(endDate) && !dateFormed.isBefore(startDate)) {
                    if (name.equals(information[NAME])) {
                        salary += Integer.parseInt(information[WORK_HOURS])
                                * Integer.parseInt(information[SALARY_PER_HOUR]);
                    }
                }
            }
            salaryAmount.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + salaryAmount;
    }
}
