package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder str = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (int i = 0; i < names.length; i++) {
            int[] salary = new int[names.length];
            for (String datum : data) {
                String[] arr = datum.split(" ");
                if ((LocalDate.parse(arr[0], formatter).isEqual(startDate)
                        || LocalDate.parse(arr[0], formatter).isAfter(startDate))
                        && (LocalDate.parse(arr[0], formatter).isEqual(endDate)
                        || LocalDate.parse(arr[0], formatter).isBefore(endDate))) {
                    if (names[i].equals(arr[1])) {
                        salary[i] += Integer.parseInt(arr[2])
                                * Integer.parseInt(arr[3]);
                    }
                }
            }
            str.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }

        return str.toString();
    }
}
