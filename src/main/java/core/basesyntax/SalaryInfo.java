package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int WORKING_HOUR = 2;
    private static final int PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        LocalDate startDate = LocalDate.parse(dateFrom, FORMAT);
        LocalDate finishDate = LocalDate.parse(dateTo, FORMAT);
        for (String name : names) {
            int salary = 0;
            result.append(name).append(" - ");
            for (String line : data) {
                LocalDate datesInLine = LocalDate.parse(line.split(" ")[DATE], FORMAT);
                if ((startDate.isBefore(datesInLine.plusDays(1)))
                        && (datesInLine.isBefore(finishDate.plusDays(1)))
                        && line.contains(name)) {
                    salary += getSalary(line);
                }
            }
            result.append(salary).append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    private int getSalary(String line) {
        String workingHourString = line.split(" ")[WORKING_HOUR];
        int workingHour = Integer.parseInt(workingHourString);
        String moneyString = line.split(" ")[PER_HOUR];
        int money = Integer.parseInt(moneyString);
        return workingHour * money;
    }
}
