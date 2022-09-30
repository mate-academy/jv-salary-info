package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] slrInfo = data[i].split(" ");
                LocalDate slrDate = LocalDate.parse(slrInfo[0], formatter);
                int hoursOfWork = Integer.parseInt(slrInfo[2]);
                int salaryPerHour = Integer.parseInt(slrInfo[3]);
                if (name.equals(slrInfo[1])
                        && !slrDate.isAfter(toDate) && !slrDate.isBefore(fromDate)) {
                    salary += hoursOfWork * salaryPerHour;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }
}
