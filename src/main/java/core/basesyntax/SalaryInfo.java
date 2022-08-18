package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] resultSalary = new int[names.length];

        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, FORMATTER);

        for (String rowData : data) {
            String[] valuesData = rowData.split(" ");
            boolean isNull = false;
            for (String value : valuesData) {
                if (value == null) {
                    isNull = true;
                    break;
                }
            }
            if (isNull) {
                continue;
            }

            int index = Arrays.asList(names).indexOf(valuesData[1]);
            LocalDate currentDate = LocalDate.parse(valuesData[0], FORMATTER);
            if (index >= 0 && !currentDate.isBefore(startDate)
                    && !currentDate.isAfter(endDate)) {
                resultSalary[index] += Integer.parseInt(valuesData[2])
                        * Integer.parseInt(valuesData[3]);
            }
        }

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(resultSalary[i]);
        }
        return result.toString();
    }
}
