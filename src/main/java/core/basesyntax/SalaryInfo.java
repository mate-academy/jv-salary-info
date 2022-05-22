package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static int salary = 0;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom,formatter);
        LocalDate toDate = LocalDate.parse(dateTo,formatter);
        StringBuilder builder = new StringBuilder();

        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            for (String datum : data) {
                String[] arr = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(arr[0], formatter);
                if (arr[1].equals(name)) {
                    if ((currentDate.isAfter(fromDate) || currentDate.isEqual(fromDate))
                            && (currentDate.isBefore(toDate) || currentDate.isEqual(toDate))) {
                        salary += Integer.parseInt(arr[2]) * Integer.parseInt(arr[3]);
                    }
                }
            }
            builder.append(salary);
            salary = 0;

        }
        return builder.toString();
    }
}
