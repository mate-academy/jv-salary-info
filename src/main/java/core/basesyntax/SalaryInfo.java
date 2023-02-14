package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] salaryInfo = new String[names.length];
        LocalDate startPeriod = initializeLocalDates(dateFrom);
        LocalDate endPeriod = initializeLocalDates(dateTo);
        int index = 0;
        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                String[] info = datum.split(" ");
                LocalDate singleDay = LocalDate.parse(info[DATE_INDEX], FORMATTER);
                if (isDateInRange(singleDay, startPeriod, endPeriod) && isNameFound(name, info)) {
                    totalSalary += calculateSalary(info);
                }
            }
            salaryInfo[index++] = name + " - " + totalSalary;
        }
        return buildReport(salaryInfo, dateFrom, dateTo);
    }

    private LocalDate initializeLocalDates(String date) {
        return LocalDate.parse(date, FORMATTER);

    }

    private boolean isDateInRange(LocalDate singleDay, LocalDate startPeriod, LocalDate endPeriod) {
        return !singleDay.isBefore(startPeriod) && !singleDay.isAfter(endPeriod);
    }

    private boolean isNameFound(String name, String[] data) {
        return name.equals(data[NAME_INDEX]);
    }

    private int calculateSalary(String[] data) {
        return (Integer.parseInt(data[HOURS_INDEX]))
                * (Integer.parseInt(data[INCOME_INDEX]));
    }

    private String buildReport(String[] salaryInfo, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");
        for (String info : salaryInfo) {
            builder.append(info).append("\n");
        }
        return builder.toString().trim();
    }
}
