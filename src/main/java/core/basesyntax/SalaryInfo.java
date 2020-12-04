package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, DMY);
        LocalDate secondDate = LocalDate.parse(dateTo, DMY);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(firstDate.format(DMY)).append(" - ")
                .append(secondDate.format(DMY));

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] buffer = record.split(" ");
                LocalDate currentDate = LocalDate.parse(buffer[0], DMY);
                if (name.equals(buffer[1]) && (currentDate.isAfter(firstDate)
                        && currentDate.isBefore(secondDate) || currentDate.isEqual(firstDate)
                        || currentDate.isEqual(secondDate))) {
                    totalSalary += Integer.parseInt(buffer[2]) * Integer.parseInt(buffer[3]);
                }
            }
            stringBuilder.append("\n").append(name).append(" - ").append(totalSalary);
        }
        return stringBuilder.toString();
    }
}
