package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String lineSeparator = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        validateInput(names, data, dateFrom, dateTo);
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int sum = 0;
            for (int i = 0; i < data.length; i++) {
                String[] dataLine = data[i].split(" ");
                if (dataLine[1].equals(name)
                        && LocalDate.parse(dataLine[0], FORMATTER)
                            .compareTo(LocalDate.parse(dateFrom, FORMATTER)) >= 0
                        && LocalDate.parse(dataLine[0], FORMATTER)
                            .compareTo(LocalDate.parse(dateTo, FORMATTER)) <= 0) {
                    sum += Integer.valueOf(dataLine[2]) * Integer.valueOf(dataLine[3]);
                }
            }
            result.append(lineSeparator)
                    .append(name)
                    .append(" - ")
                    .append(sum);
        }
        return result.toString();
    }

    public void validateInput(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names.length == 0 || data.length == 0 || dateFrom == null || dateTo == null) {
            throw new DataFormatException("Data is empty");
        }
    }
}

