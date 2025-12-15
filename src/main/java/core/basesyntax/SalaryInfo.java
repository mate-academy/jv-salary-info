package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_NUMBER = 0;
    private static final int NAME_NUMBER = 1;
    private static final int HOURS_NUMBER = 2;
    private static final int SALARY_NUMBER = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder salaryReport = new StringBuilder();
        salaryReport.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int fullSalary = 0;
            for (String info : data) {
                String[] parts = info.split(" ");
                if (name.equals(parts[NAME_NUMBER])) {
                    LocalDate date = LocalDate.parse(parts[DATE_NUMBER], FORMATTER);
                    if (!date.isBefore(firstDate) && !date.isAfter(lastDate)) {
                        fullSalary += Integer.parseInt(parts[HOURS_NUMBER])
                                * Integer.parseInt(parts[SALARY_NUMBER]);
                    }
                }
            }
            salaryReport.append(name)
                    .append(" - ")
                    .append(fullSalary)
                    .append(System.lineSeparator());
        }
        return salaryReport.toString().trim();
    }
}
