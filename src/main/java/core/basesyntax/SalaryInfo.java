package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dateDateTo = LocalDate.parse(dateTo, formatter);
        int[] salaries = new int[names.length];
        StringBuilder output = new StringBuilder("Report for period ");
        output.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            for (String dataLine : data) {
                final LocalDate date = LocalDate.parse(dataLine.split(" ")[0], formatter);
                if (dataLine.split(" ")[1].contains(names[i])
                        && (dateDateFrom.isBefore(date) && dateDateTo.isAfter(date)
                        || dateDateFrom.isEqual(date) || dateDateTo.isEqual(date))) {
                    salaries[i] += Integer.parseInt(dataLine.split(" ")[2])
                            * Integer.parseInt(dataLine.split(" ")[3]);
                }
            }
        }
        for (int i = 0; i < names.length - 1; i++) {
            output.append(names[i]).append(" - ").append(salaries[i]);
            output.append(System.lineSeparator());
        }
        output.deleteCharAt(output.length() - 1);
        return output.toString();
    }
}
