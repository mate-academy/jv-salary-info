package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int sumSalary = 0;
            for (String dataSplit : data) {
                String[] informationSplit = dataSplit.split(" ");
                LocalDate currentDate = LocalDate.parse(informationSplit[INDEX_DATE], FORMATTER);
                if ((currentDate.equals(from) || currentDate.isAfter(from))
                        && (currentDate.equals(to) || currentDate.isBefore(to))
                        && informationSplit[INDEX_NAME].equals(name)) {
                    sumSalary += Integer.parseInt(informationSplit[INDEX_HOURS])
                            * Integer.parseInt(informationSplit[INDEX_SALARY_PER_HOUR]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(sumSalary);
        }
        return builder.toString();
    }
}
