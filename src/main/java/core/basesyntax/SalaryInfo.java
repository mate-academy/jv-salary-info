package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.getProperty("line.separator"));
        for (String name : names) {
            int bufferSum = 0;
            for (String datum : data) {
                String[] personalData = datum.split(" ");
                if (name.equals(personalData[1])
                        && LocalDate.parse(dateFrom, formatter)
                        .isBefore(LocalDate.parse(personalData[0], formatter))
                        && LocalDate.parse(dateTo, formatter)
                        .plusDays(1).isAfter((LocalDate.parse(personalData[0], formatter)))
                ) {
                    bufferSum += Integer.parseInt(personalData[2])
                            * Integer.parseInt(personalData[3]);
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(bufferSum)
                    .append(System.getProperty("line.separator"));
        }
        return result.toString().trim();
    }
}
