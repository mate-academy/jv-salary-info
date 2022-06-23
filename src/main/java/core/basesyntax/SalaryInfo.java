package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = getDate(dateFrom);
        LocalDate to = getDate(dateTo);
        int[] daySalariesOfChosenNames = getSalaries(names, data, from, to);
        return createReportForDateRange(names, dateFrom, dateTo, daySalariesOfChosenNames);
    }

    private int[] getSalaries(String[] names, String[] data, LocalDate from, LocalDate to) {
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] s = data[j].split(" ");
                LocalDate currentDate = getDate(s[0]);
                String name = s[1];
                int salaryByDay = Integer.parseInt(s[2]) * Integer.parseInt(s[3]);
                if (name.equals(names[i])
                        && (currentDate.equals(from) || currentDate.isAfter(from))
                        && (currentDate.equals(to) || currentDate.isBefore(to))) {
                    salary[i] += salaryByDay;
                }
            }
        }
        return salary;
    }

    private String createReportForDateRange(String[] names, String dateFrom,
                                            String dateTo, int[] salaries) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            builder.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }
        return builder.substring(0, builder.length() - 2);
    }

    private LocalDate getDate(String dateFrom) {
        return LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
