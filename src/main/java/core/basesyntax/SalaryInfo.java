package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salary = new int[names.length];
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        for (String information : data) {
            String[] formattedData = information.split(" ");
            LocalDate workDay = LocalDate.parse(formattedData[0], formatter);
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(formattedData[1]) && compareDate(workDay, from, to)) {
                    int hours = Integer.parseInt(formattedData[2]);
                    int salaryPerHour = Integer.parseInt(formattedData[3]);
                    salary[i] += hours * salaryPerHour;
                    break;
                }
            }
        }
        return formatInfo(salary, names, dateFrom, dateTo);
    }

    private static boolean compareDate(LocalDate date, LocalDate from, LocalDate to) {
        return (date.isAfter(from) || date.isEqual(from))
                && (date.isEqual(to) || date.isBefore(to));

    }

    private static String formatInfo(int[] salary, String[] names, String from, String to) {
        StringBuilder info = new StringBuilder("Report for period " + from + " - " + to);
        for (int i = 0; i < names.length; i++) {
            info.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salary[i]);
        }
        return info.toString();
    }

}

