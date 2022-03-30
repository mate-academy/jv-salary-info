package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo,FORMATTER);
        StringBuilder stringBuilder = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] arrayOfData;

        for (String name: names) {
            int salary = 0;
            for (String line: data) {
                arrayOfData = line.split(" ");
                LocalDate correctData = LocalDate.parse(arrayOfData[0],FORMATTER);
                if (localDateFrom.compareTo(correctData) <= 0
                        && localDateTo.compareTo(correctData) >= 0
                        && arrayOfData[1].equals(name)) {
                    salary += (Integer.parseInt(arrayOfData[3])
                            * Integer.parseInt(arrayOfData[2]));
                }
            }

            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
