package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_PAY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String namePerson : names) {
            int salary = 0;
            for (String entryData : data) {
                String[] splittedData = entryData.split(" ");
                if (namePerson.equals(splittedData[INDEX_NAME])
                        && !LocalDate.parse(splittedData[INDEX_DATE], DATE_FORMAT)
                        .isBefore(fromDate)
                        && !LocalDate.parse(splittedData[INDEX_DATE], DATE_FORMAT)
                        .isAfter(toDate)) {
                    salary += Integer.parseInt(splittedData[INDEX_PAY])
                            * Integer.parseInt(splittedData[INDEX_HOURS]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(namePerson)
                    .append(" - ")
                    .append(salary);
        }
        return builder.toString();
    }
}
