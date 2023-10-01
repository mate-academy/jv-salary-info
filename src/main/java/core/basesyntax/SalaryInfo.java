package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        String separator = " - ";
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        LocalDate particularDay = null;

        builder.append(dateFrom).append(separator).append(dateTo).append(System.lineSeparator());


        System.out.println(builder);
        return "hello";
    }
}
