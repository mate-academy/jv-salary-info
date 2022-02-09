package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int[] payment = new int[names.length];

        for (int i = 0; i < names.length; i++) {
            for (String dataRecord : data) {
                String[] arr = dataRecord.split(" ");
                if ((LocalDate.parse(arr[0], formatter).isEqual(startDate)
                        || LocalDate.parse(arr[0], formatter).isAfter(startDate))
                        && (LocalDate.parse(arr[0], formatter).isEqual(endDate)
                        || LocalDate.parse(arr[0], formatter).isBefore(endDate))) {
                    if (names[i].equals(arr[1])) {
                        payment[i] += Integer.parseInt(arr[2])
                                * Integer.parseInt(arr[3]);
                    }
                }
            }
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(payment[i]);
        }
        return result.toString();
    }
}
