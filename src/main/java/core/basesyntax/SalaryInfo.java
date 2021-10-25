package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom,DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo,DATE_FORMATTER);
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        int salaryForPeriod;
        for (String currentName : names) {
            salaryForPeriod = 0;
            for (String dataLine : data) {
                String[] formattedDataLine = dataLine.split(" ");
                LocalDate currentDate = LocalDate.parse(formattedDataLine[0],DATE_FORMATTER);
                if (currentDate.equals(formattedDataLine[1])
                        && from.compareTo(currentDate) <= 0
                        && to.compareTo(currentDate) >= 0) {
                    salaryForPeriod += Integer.parseInt(formattedDataLine[2])
                            * Integer.parseInt(formattedDataLine[3]);
                }
            }
            result.append(currentName)
                    .append(" - ")
                    .append(salaryForPeriod);
            if (names.length != 1 && !currentName.equals(names[names.length - 1])) {
                result.append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
