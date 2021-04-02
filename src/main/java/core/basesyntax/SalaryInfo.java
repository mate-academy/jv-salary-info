package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        LocalDate[] workingDays = new LocalDate[data.length];
        long[] salaryAllEmployees = new long[names.length];

        for (int i = 0; i < data.length; i++) {
            workingDays[i] = LocalDate.parse(data[i].substring(0, 10), DATE_FORMAT);
        }

        for (int i = 0; i < workingDays.length; i++) {
            for (int j = 0; j < names.length; j++) {
                if (workingDays[i].compareTo(fromDate) >= 0
                        && workingDays[i].compareTo(toDate) <= 0
                        && names[j].equals(data[i].split(" ")[1])) {
                    salaryAllEmployees[j] += Integer.parseInt(data[i].split(" ")[2])
                            * Integer.parseInt(data[i].split(" ")[3]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (int i = 0; i < names.length; i++) {
            sb = i == 0 ? sb
                    .append(names[i]).append(" - ")
                    .append(salaryAllEmployees[i])
                    : sb.append("\n").append(names[i]).append(" - ")
                    .append(salaryAllEmployees[i]);
        }
        return sb.toString();
    }
}
