package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Report for period %s - %s"
                + System.lineSeparator(), dateFrom, dateTo));
        String rowTemplate = "%s - %s";
        for (int i = 0; i < names.length; i++) {
            int personTotalSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] parsedDataRow = parseDataRow(data[j]);
                DateTimeFormatter formatter
                        = DateTimeFormatter.ofPattern("d.MM.yyyy");
                if (LocalDate.parse(parsedDataRow[0], formatter).isAfter(
                        LocalDate.parse(dateTo, formatter)) || LocalDate.parse(
                        parsedDataRow[0], formatter).isBefore(
                        LocalDate.parse(dateFrom, formatter))) {
                    continue;
                }
                if (!names[i].equals(parsedDataRow[1])) {
                    continue;
                }
                personTotalSalary += calculateRowSalary(parsedDataRow[2], parsedDataRow[3]);
            }
            if (i == 0) {
                result.append(String.format(rowTemplate, names[i], personTotalSalary));
            } else {
                result.append(System.lineSeparator()).append(String.format(rowTemplate,
                        names[i], personTotalSalary));
            }
        }
        return result.toString();
    }

    private static int calculateRowSalary(String hours, String perHour) {
        return Integer.parseInt(hours) * Integer.parseInt(perHour);
    }

    private static String[] parseDataRow(String dataRow) {
        return dataRow.split(" ");
    }
}
