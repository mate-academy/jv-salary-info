package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            int salary = 0;

            for (int j = 0; j < data.length; j++) {
                String[] dataAll = data[j].split(" ");
                if (LocalDate.parse(dataAll[0], formatter).isBefore(toDate.plusDays(1))
                        && LocalDate.parse(dataAll[0], formatter).isAfter(fromDate)
                        && dataAll[1].equals(names[i])) {
                    salary += Integer.parseInt(dataAll[2]) * Integer.parseInt(dataAll[3]);
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
