package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATA_SEPARATOR = " ";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NUMBER_OF_DAYS_FOR_BORDER_SHIFT = 1;
    private Report report;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        LocalDate dateFromBorder = stringDateIntoLocalDate(dateFrom)
                .minusDays(NUMBER_OF_DAYS_FOR_BORDER_SHIFT);
        LocalDate dateToBorder = stringDateIntoLocalDate(dateTo)
                .plusDays(NUMBER_OF_DAYS_FOR_BORDER_SHIFT);
        for (String line : data) {
            String[] info = line.split(DATA_SEPARATOR);
            LocalDate workDate = stringDateIntoLocalDate(info[0]);
            if (workDate.isAfter(dateFromBorder) && workDate.isBefore(dateToBorder)) {
                String workerName = info[1];
                int workingHours = Integer.parseInt(info[2]);
                int payPerHour = Integer.parseInt(info[3]);
                int salaryPosition = findWorkerNamePosition(names, workerName);
                salaries[salaryPosition] += workingHours * payPerHour;
            }
        }
        report = new Report(dateFrom, dateTo, names, salaries);
        return report.toString();
    }

    private int findWorkerNamePosition(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private LocalDate stringDateIntoLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
