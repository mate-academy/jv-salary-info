package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");
        int count = 0;
        for (String name : names) {
            int sum = 0;
            for (String elementOfData : data) {
                String[] elements = elementOfData.split(" ");
                if (elements[1].equals(name)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    LocalDate docDate = null;
                    LocalDate fromDate = null;
                    LocalDate toDate = null;
                    try {
                        docDate = LocalDate.parse(elements[0], formatter);;
                        fromDate = LocalDate.parse(dateFrom, formatter);
                        toDate = LocalDate.parse(dateTo, formatter);
                        fromDate = fromDate.minusDays(1);
                        toDate = toDate.plusDays(1);
                    } catch (DateTimeParseException e) {
                        e.printStackTrace();
                    }
                    if (docDate.isAfter(fromDate) && docDate.isBefore(toDate)) {
                        sum += Integer.parseInt(elements[2]) * Integer.parseInt(elements[3]);
                    }
                }
            }
            if (count == names.length - 1) {
                result.append(name).append(" - ").append(sum);
            } else {
                result.append(name).append(" - ").append(sum).append(System.lineSeparator());
                count++;
            }
        }
        return result.toString();
    }
}
