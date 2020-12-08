package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = "\n";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append("Report ").append("for ").append("period ")
                .append(localDateFrom.format(DATE_FORMATTER)).append(" - ")
                .append(localDateTo.format(DATE_FORMATTER));
        for (String name: names) {
            int salary = 0;
            for (String stringData: data) {
                String[] splitedData = stringData.split(" ");
                LocalDate localDate = LocalDate.parse(splitedData[0], DATE_FORMATTER);
                if (!localDateFrom.isEqual(localDateTo)
                        && splitedData[1].equals(name)
                        && (localDate.isAfter(localDateFrom)
                        || localDate.isEqual(localDateFrom))
                        && (localDate.isBefore(localDateTo) || localDate.isEqual(localDateTo))) {
                    salary += Integer.parseInt(splitedData[2]) * Integer.parseInt(splitedData[3]);
                }
            }
            report.append(LINE_SEPARATOR).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
