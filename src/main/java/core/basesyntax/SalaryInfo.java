package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = (
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
    );

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder(
                "Report for period "
        ).append(dateFrom).append(" - ").append(dateTo).append("\n");

        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        for (String name : names) {
            int salary = 0;
            for (String salaryData : data) {
                String[] parsedData = salaryData.split(" ");
                LocalDate date = LocalDate.parse(parsedData[0], DATE_FORMATTER);
                String workerName = parsedData[1];
                int hours = Integer.parseInt(parsedData[2]);
                int salaryPerHour = Integer.parseInt(parsedData[3]);

                if (
                        workerName.equals(name) && (date.isEqual(fromDate)
                                || date.isEqual(toDate)
                                || (date.isAfter(fromDate) && date.isBefore(toDate)))
                ) {
                    salary += hours * salaryPerHour;
                }
            }
            result.append(name).append(" - ").append(salary).append("\n");
        }

        return result.deleteCharAt(result.length() - 1).toString();
    }
}
