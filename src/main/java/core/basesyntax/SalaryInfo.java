package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_ENDING_DATE = 10;
    private static final int INDEX_OF_STARTING_DATE = 0;
    private static final int INDEX_OF_HOURS_NUMBER = 2;
    private static final int INDEX_OF_SALARY_FOR_HOUR = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(fromDate.format(dateTimeFormatter)).append(" - ")
                .append(toDate.format(dateTimeFormatter));

        for (String name : names) {
            int count = 0;
            for (String record : data) {
                LocalDate actualDate = LocalDate
                        .parse(record.substring(INDEX_OF_STARTING_DATE, INDEX_OF_ENDING_DATE),
                                dateTimeFormatter);
                String[] dataParts = record.split(" ");
                if (dataParts[1].equals(name)
                        && !actualDate.isBefore(fromDate) && !actualDate.isAfter(toDate)) {
                    count += Integer.parseInt(dataParts[INDEX_OF_HOURS_NUMBER])
                            * Integer.parseInt(dataParts[INDEX_OF_SALARY_FOR_HOUR]);
                }
            }
            report.append("\n" + name + " - " + count);
        }

        return report.toString();
    }
}
