package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {

    private static final String[] scriptArray = {
            "25.04.2019 John 60 50",
            "25.04.2019 Andrew 3 200",
            "25.04.2019 Kate 10 100",

            "26.04.2019 Andrew 3 200",
            "26.04.2019 Kate 9 100",

            "27.04.2019 John 7 100",
            "27.04.2019 Kate 3 80",
            "27.04.2019 Andrew 8 100"
    };

    private static final String[] sampleNames = {"John", "Andrew", "Kate"};

    private static final String[] dates = {
            "24.04.2019",
            "25.04.2019",
            "26.04.2019",
            "27.04.2019"
    };

    public static void main(String[] args) {

        System.out.println(getSalaryInfo(sampleNames, scriptArray, dates[0], dates[1]));
        System.out.println();
    }

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        Map<String, Integer> values = new HashMap<>();
        for (int i = 0; i < data.length; i++) {
            String[] parsedDataRow = parseDataRow(data[i]);
            DateTimeFormatter formatter
                    = DateTimeFormatter.ofPattern("d.MM.yyyy");
            if (LocalDate.parse(parsedDataRow[0], formatter).isAfter(
                    LocalDate.parse(dateTo, formatter)) || LocalDate.parse(
                    parsedDataRow[0], formatter).isBefore(
                    LocalDate.parse(dateFrom, formatter))) {
                continue;
            }
            int rowSalary = calculateRowSalary(parsedDataRow[2], parsedDataRow[3]);
            if (values.size() == 0 || !values.containsKey(parsedDataRow[1])) {
                values.put(parsedDataRow[1], rowSalary);
            } else {
                values.put(parsedDataRow[1], values.get(parsedDataRow[1]) + rowSalary);
            }
        }
        String resultTemplate = String.format("Report for period %s - %s" + System.lineSeparator()
                        + generateSalaryRows(values, names),
                dateFrom, dateTo);
        return resultTemplate;
    }

    private static int calculateRowSalary(String hours, String perHour) {
        return Integer.parseInt(hours) * Integer.parseInt(perHour);
    }

    private static String[] parseDataRow(String dataRow) {
        return dataRow.split(" ");
    }

    private static String generateSalaryRows(
            Map<String, Integer> values,
            String[] orderedNames) {
        String template = "%s - %s";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < orderedNames.length; i++) {
            if (i == 0) {
                result.append(String.format(template, orderedNames[i],
                        values.get(orderedNames[i]) == null ? 0 : values.get(orderedNames[i])));
            } else {
                result.append(System.lineSeparator())
                        .append(String.format(template, orderedNames[i],
                                values.get(orderedNames[i]) == null ? 0 :
                                        values.get(orderedNames[i])));
            }
        }
        return result.toString();
    }

}
