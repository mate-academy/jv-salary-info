package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] splitData;
        int salaryPerHour;
        int workingHours;
        LocalDate localDateFrom = toLocalDate(dateFrom);
        LocalDate localDateTo = toLocalDate(dateTo);
        int[] salaries = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            splitData = data[i].split(" ");
            workingHours = Integer.parseInt(splitData[2]);
            salaryPerHour = Integer.parseInt(splitData[3]);
            LocalDate date = toLocalDate(splitData[0]);
            if (date.isAfter(localDateFrom)
                    && date.isBefore(localDateTo)
                    || date.isEqual(localDateFrom)
                    || date.isEqual(localDateTo)) {
                for (int z = 0; z < names.length; z++) {
                    if (names[z].equals(splitData[1])) {
                        salaries[z] += workingHours * salaryPerHour;
                    }
                }
            }
        }
        return buildReport(names, salaries, dateFrom, dateTo);
    }

    private LocalDate toLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    private String buildReport(String[] names, int[] salaries, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            reportBuilder.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return reportBuilder.toString();
    }
}
