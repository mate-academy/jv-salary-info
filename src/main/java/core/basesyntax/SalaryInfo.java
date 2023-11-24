package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DAY_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryReport = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] splittedLine = line.split(" ");
                LocalDate date = LocalDate.parse(splittedLine[DAY_INDEX], FORMATTER);
                String nameFromLine = splittedLine[NAME_INDEX];
                int workingHour = Integer.parseInt(splittedLine[WORKING_HOUR_INDEX]);
                int incomePerHour = Integer.parseInt(splittedLine[INCOME_PER_HOUR_INDEX]);
                if (nameFromLine.equals(name)
                        && (date.isAfter(localDateFrom)
                        || date.isEqual(localDateFrom))
                        && (date.isBefore(localDateTo)
                        || date.isEqual(localDateTo))) {
                    salary += workingHour * incomePerHour;
                }
            }
            String lineSeparator = System.lineSeparator();
            salaryReport.append(lineSeparator).append(name).append(" - ").append(salary);
        }
        return salaryReport.toString();
    }
}
