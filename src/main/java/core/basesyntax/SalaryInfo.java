package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int sumSalary = 0;
            for (String datum : data) {
                String[] elements = datum.split("\\s");
                if (name.equals(elements[1])
                        && dateIsInRange(dateFrom, dateTo, elements[0])) {
                    sumSalary += Integer.parseInt(elements[2])
                            * Integer.parseInt(elements[3]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(sumSalary);
        }

        return builder.toString();
    }

    private boolean dateIsInRange(String onlyDateFrom, String onlyDateTo, String workDay) {
        LocalDate from = getDate(onlyDateFrom);
        LocalDate to = getDate(onlyDateTo);
        LocalDate day = getDate(workDay);
        return (from.equals(day) || to.equals(day)) || (day.isBefore(to) && day.isAfter(from));

    }

    private LocalDate getDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
