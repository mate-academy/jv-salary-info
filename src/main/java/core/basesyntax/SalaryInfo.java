package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ").append(dateFrom)
                                                                      .append(" - ")
                                                                      .append(dateTo)
                                                                      .append("\n");
        ArrayList<String> processedNames = new ArrayList<>();
        Arrays.stream(data)
                .filter(dataRow -> Arrays.asList(names).contains(dataRow.split(" ")[1]))
                .forEach(dataRow -> {
                    String rowName = dataRow.split(" ")[1];
                    if (!processedNames.contains(rowName)) {
                        int salary = Arrays.stream(data)
                                .filter(row -> row.split(" ")[1].equals(rowName))
                                .mapToInt((row -> {
                                    String[] dividedRow = row.split(" ");
                                    LocalDate rowDate =
                                            LocalDate.parse(dividedRow[0], DATE_FORMATTER);
                                    return (rowDate
                                                .isAfter(LocalDate.parse(dateFrom, DATE_FORMATTER))
                                            && rowDate
                                                .isBefore(LocalDate.parse(dateTo, DATE_FORMATTER)
                                                        .plusDays(1)))
                                            ? Integer.parseInt(dividedRow[2])
                                                * Integer.parseInt(dividedRow[3])
                                            : 0;
                                }))
                                .sum();
                        report.append(rowName).append(" - ").append(salary).append("\n");
                        processedNames.add(rowName);
                    }
                });
        return report.deleteCharAt(report.length() - 1).toString();
    }
}
