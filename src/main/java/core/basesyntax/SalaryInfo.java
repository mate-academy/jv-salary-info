package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder str = new StringBuilder();
        str.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        LocalDate formatDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate formatDateTo = LocalDate.parse(dateTo, formatter);
        for (String name : names) {
            int totalSalary = 0;
            for (String datum : data) {
                if (name.equals(datum.substring(11, 11 + name.length()))) {
                    if (LocalDate.parse(datum.substring(0, 10), formatter)
                            .isAfter(formatDateFrom)
                            && LocalDate.parse(datum.substring(0, 10), formatter)
                            .isBefore(formatDateTo.plusDays(1))) {
                        int days = Integer.parseInt(datum.substring(11 + name.length() + 1,
                                datum.lastIndexOf(" ")));
                        int salary = Integer.parseInt(datum.substring(datum.lastIndexOf(" ") + 1));
                        totalSalary += days * salary;
                    }
                }
            }
            str.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
        }
        return str.toString().trim();
    }
}
