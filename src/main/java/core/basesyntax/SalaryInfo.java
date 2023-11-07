package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate minDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate maxDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String dataRow : data) {
                String[] dataRowArray = dataRow.split(" ");
                if (name.equals(dataRowArray[1])) {
                    LocalDate rowDate = LocalDate.parse(dataRowArray[0], FORMATTER);
                    if ((rowDate.isAfter(minDate) && rowDate.isBefore(maxDate))
                            || rowDate.equals(minDate)
                            || rowDate.equals(maxDate)) {
                        salary += Integer.parseInt(dataRowArray[2])
                                * Integer.parseInt(dataRowArray[3]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return builder.toString();
    }
}
