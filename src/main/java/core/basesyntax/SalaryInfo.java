package core.basesyntax;

import java.time.LocalDate;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        int sum = 0;

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
                        && LocalDate.parse(dateFrom, formatter).isBefore(LocalDate.parse(personalData[0], formatter))
                        && LocalDate.parse(dateTo, formatter).isAfter((LocalDate.parse(personalData[0], formatter)))
                ) {
                    sum += Integer.parseInt(personalData[2]) * Integer.parseInt(personalData[3]);
                }
            }
            result.append(name).append(" - ").append(sum).append(System.getProperty("line.separator"));
        }
        return result.toString();
    }
}
