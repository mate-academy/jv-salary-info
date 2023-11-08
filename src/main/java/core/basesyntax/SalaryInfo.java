package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate deadLineDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dataString : data) {
                String[] dataStringArray = dataString.split(" ");
                if (name.equals(dataStringArray[1])) {
                    LocalDate rowDate = LocalDate.parse(dataStringArray[0], FORMATTER);
                    if ((rowDate.isAfter(startDate) && rowDate.isBefore(deadLineDate))
                            || rowDate.equals(startDate)
                            || rowDate.equals(deadLineDate)) {
                        salary += Integer.parseInt(dataStringArray[2])
                                * Integer.parseInt(dataStringArray[3]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return builder.toString();
    }
}
