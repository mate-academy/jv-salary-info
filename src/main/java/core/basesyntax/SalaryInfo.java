package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            builder.append(System.lineSeparator());
            for (String line : data) {
                String[] arrayOfData = line.split(" ");
                if (name.equals(arrayOfData[1])) {
                    LocalDate currentDate = LocalDate.parse(arrayOfData[0], FORMATTER);
                    if (localDateFrom.compareTo(currentDate) <= 0
                            && localDateTo.compareTo(currentDate) >= 0
                            && arrayOfData[1].equals(name)) {
                        salary += Integer.parseInt(arrayOfData[2])
                                * Integer.parseInt(arrayOfData[3]);
                    }
                }
            }
            builder.append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
