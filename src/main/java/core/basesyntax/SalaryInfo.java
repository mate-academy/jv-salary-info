package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    private static final int DATE_FROM_DATA = 0;
    private static final int NAME_FROM_DATA = 1;
    private static final int HOURS_FROM_DATA = 2;
    private static final int SALARY_FROM_DATA = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws ArrayIndexOutOfBoundsException {
        int[] salary = new int[names.length];
        LocalDate from = parseDate(dateFrom, formatter);
        LocalDate to = parseDate(dateTo, formatter);
        for (String information : data) {
            String[] formattedData = information.split(" ");
            LocalDate workDay = parseDate(formattedData[DATE_FROM_DATA], formatter);
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(formattedData[NAME_FROM_DATA])
                        && compareDate(workDay, from, to)) {
                    int hours = Integer.parseInt(formattedData[HOURS_FROM_DATA]);
                    int salaryPerHour = Integer.parseInt(formattedData[SALARY_FROM_DATA]);
                    salary[i] += hours * salaryPerHour;
                    break;
                }
            }
        }
        return formatInfo(salary, names, dateFrom, dateTo);
    }

    private boolean compareDate(LocalDate date, LocalDate from, LocalDate to) {
        return !date.isBefore(from) && !date.isAfter(to);

    }

    private String formatInfo(int[] salary, String[] names, String from, String to) {
        StringBuilder info = new StringBuilder("Report for period ");
        info.append(from).append(" - ").append(to);
        for (int i = 0; i < names.length; i++) {
            info.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary[i]);
        }
        return info.toString();
    }

    private LocalDate parseDate(String date, DateTimeFormatter formatter) {
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("wrong date format");
        }
    }
}
