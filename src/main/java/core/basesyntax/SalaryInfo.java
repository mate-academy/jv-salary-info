package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder reportAboutSalary = new StringBuilder();

        reportAboutSalary.append("Report ").append("for ").append("period ")
                .append(localDateFrom.format(DATE_FORMATTER)).append(" - ")
                .append(localDateTo.format(DATE_FORMATTER));

        for (int i = 0; i < names.length; i++) {
            int salary = 0;

            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split(" ");
                LocalDate localDate = LocalDate.parse(splitData[0], DATE_FORMATTER);

                if (splitData[1].equals(names[i])
                        && (localDate.isAfter(localDateFrom) || localDate.isEqual(localDateFrom))
                        && (localDate.isBefore(localDateTo) || localDate.isEqual(localDateTo))
                        && !localDateFrom.isEqual(localDateTo)) {
                    salary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            reportAboutSalary.append("\n").append(names[i]).append(" - ").append(salary);
        }

        return reportAboutSalary.toString();
    }
}
