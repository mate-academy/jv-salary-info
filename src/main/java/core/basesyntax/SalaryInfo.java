package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static int salary;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static int INDEX_OF_HOURS = 2;
    private static int INDEX_OF_SALARY_PER_HOUR = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {

        LocalDate fromDate = LocalDate.parse(dateFrom,FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo,FORMATTER);
        StringBuilder builder = new StringBuilder();

        builder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            for (String datum : data) {
                String[] arr = datum.split(" ");
                LocalDate currentDate = LocalDate.parse(arr[0], FORMATTER);
                if (arr[1].equals(name)) {
                    if ((currentDate.isAfter(fromDate) || currentDate.isEqual(fromDate))
                            && (currentDate.isBefore(toDate) || currentDate.isEqual(toDate))) {
                        salary += Integer.parseInt(arr[INDEX_OF_HOURS])
                                * Integer.parseInt(arr[INDEX_OF_SALARY_PER_HOUR]);
                    }
                }
            }
            builder.append(salary);
            salary = 0;
        }
        return builder.toString();
    }
}
