package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder report = new StringBuilder();
        report.append("Report ").append("for ").append("period ")
                .append(localDateFrom.format(DATE_FORMATTER)).append(" - ")
                .append(localDateTo.format(DATE_FORMATTER));
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] splitedData = data[j].split(" ");
                LocalDate localDate = LocalDate.parse(splitedData[0], DATE_FORMATTER);
                if (splitedData[1].equals(names[i])
                        && (localDate.isAfter(localDateFrom) || localDate.isEqual(localDateFrom))
                        && (localDate.isBefore(localDateTo) || localDate.isEqual(localDateTo))
                        && !localDateFrom.isEqual(localDateTo)) {
                    salary += Integer.parseInt(splitedData[2]) * Integer.parseInt(splitedData[3]);
                }
            }
            report.append("\n").append(names[i]).append(" - ").append(salary);
        }
        return report.toString();
    }
}
