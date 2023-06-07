package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static int PARSED_ROW_DATE_INDEX = 0;
    private static int PARSED_ROW_NAME_INDEX = 1;
    private static int PARSED_ROW_HOURS_INDEX = 2;
    private static int PARSED_ROW_RATE_PER_HOUR_INDEX = 3;


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
                if (!(LocalDate.parse(parsedDataRow[PARSED_ROW_DATE_INDEX], formatter).isAfter(
                        LocalDate.parse(dateTo, formatter)) || LocalDate.parse(
                        parsedDataRow[PARSED_ROW_DATE_INDEX], formatter).isBefore(
                        LocalDate.parse(dateFrom, formatter)))
                        && names[i].equals(parsedDataRow[PARSED_ROW_NAME_INDEX])) {
                    personTotalSalary +=
                            calculateRowSalary(parsedDataRow[PARSED_ROW_HOURS_INDEX],
                                    parsedDataRow[PARSED_ROW_RATE_PER_HOUR_INDEX]);
                }
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
