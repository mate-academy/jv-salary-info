package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        LocalDate startDate = LocalDate.parse(dateFrom, FORMAT);
        LocalDate finishDate = LocalDate.parse(dateTo, FORMAT);
        for (String name : names) {
            int salary = 0;
            result.append(name).append(" - ");
            for (String line : data) {
                LocalDate datesInLine = LocalDate.parse(line.substring(0, 10), FORMAT);
                if ((startDate.isBefore(datesInLine.plusDays(1)))
                        && (datesInLine.isBefore(finishDate.plusDays(1)))
                        && line.contains(name)) {
                    salary += getSalary(line, name);
                }
            }
            result.append(salary).append(System.lineSeparator());
        }
        result.setLength(result.length() - System.lineSeparator().length());
        return result.toString();
    }

    private int getSalary(String line, String name) {
        int startIndexWorkingHour = line.indexOf(name) + name.length() + 1;
        String workingHourString = line.substring(startIndexWorkingHour, line.lastIndexOf(" "));
        int workingHour = Integer.parseInt(workingHourString);
        String moneyString = line.substring(line.lastIndexOf(" ") + 1, line.length());
        int money = Integer.parseInt(moneyString);
        return workingHour * money;
    }
}