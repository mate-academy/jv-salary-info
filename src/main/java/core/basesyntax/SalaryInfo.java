package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaryOfEmployee = new int[names.length];
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromCalendar = LocalDate.parse(dateFrom, FORMATTER).minusDays(1);
        LocalDate dateToCalendar = LocalDate.parse(dateTo, FORMATTER).plusDays(1);

        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split("\\s");
                if ((names[i].equals(splitData[1])) && LocalDate.parse(splitData[0], FORMATTER)
                        .isAfter(dateFromCalendar)
                        && LocalDate.parse(splitData[0], FORMATTER).isBefore(dateToCalendar)) {
                    salaryOfEmployee[i] += Integer.parseInt(splitData[2])
                            * Integer.parseInt(splitData[3]);
                }
            }
        }

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator() + names[i] + " - " + salaryOfEmployee[i]);
        }

        return report.toString();
    }
}
