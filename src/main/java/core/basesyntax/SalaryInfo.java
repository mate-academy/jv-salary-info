package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_INDEX = 3;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
    private int totalSalary;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] salaryInfo = new String[names.length];
        initializeLocalDates(dateFrom, dateTo);
        for (int i = 0; i < names.length; i++) {
            for (int y = 0; y < data.length; y++) {
                String[] info = data[y].split(" ");
                LocalDate singleDay = LocalDate.parse(info[DATE_INDEX], FORMATTER);
                if (dateIsValid(singleDay, startPeriod, endPeriod) && nameIsValid(names[i], info)) {
                    totalSalary += calculateSalary(info);
                }
                salaryInfo[i] = names[i] + " - " + totalSalary;
            }
            resetSalary();
        }
        return buildReport(salaryInfo, dateFrom, dateTo);
    }

    private void initializeLocalDates(String dateFrom, String dateTo) {
        startPeriod = LocalDate.parse(dateFrom, FORMATTER);
        endPeriod = LocalDate.parse(dateTo, FORMATTER);
    }

    private boolean dateIsValid(LocalDate singleDay, LocalDate startPeriod, LocalDate endPeriod) {
        return !singleDay.isBefore(startPeriod) && !singleDay.isAfter(endPeriod);
    }

    private boolean nameIsValid(String name, String[] data) {
        return name.equals(data[NAME_INDEX]);
    }

    private void resetSalary() {
        totalSalary = 0;
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
