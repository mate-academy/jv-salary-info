package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final byte INDEX_OF_DATE = 0;
    private static final byte INDEX_OF_NAME = 1;
    private static final byte INDEX_OF_HOURS = 2;
    private static final byte INDEX_OF_PAYMENT = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo)
                .append(System.lineSeparator());
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (String lineFromData : data) {
                String[] arrayFromLine = lineFromData.split(" ");
                LocalDate dateFromLine = LocalDate
                        .parse(arrayFromLine[INDEX_OF_DATE], DATE_FORMATTER);
                if (!(dateFromLine.isBefore(startDate) || dateFromLine.isAfter(endDate))
                        && arrayFromLine[INDEX_OF_NAME].equals(names[i])) {
                    salary += Integer.parseInt(arrayFromLine[INDEX_OF_HOURS])
                            * Integer.parseInt(arrayFromLine[INDEX_OF_PAYMENT]);
                }
            }
            if (i == names.length - 1) {
                result.append(names[i]).append(" - ").append(salary);
                break;
            }
            result.append(names[i]).append(" - ").append(salary)
                    .append(System.lineSeparator());
            salary = 0;
        }
        return result.toString();
    }
}
