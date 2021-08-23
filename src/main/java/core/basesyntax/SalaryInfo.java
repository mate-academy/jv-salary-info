package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String intel : data) {
                String[] splitIntel = intel.split(" ");
                LocalDate currentDate = LocalDate.parse(splitIntel[0], FORMATTER);
                if (name.equals(splitIntel[1])
                        && !currentDate.isBefore(localDateFrom)
                        && !currentDate.isAfter(localDateTo)) {
                    totalSalary += Integer.parseInt(splitIntel[2])
                            * Integer.parseInt(splitIntel[3]);
                }
            }
            sb.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return sb.toString();
    }
}
