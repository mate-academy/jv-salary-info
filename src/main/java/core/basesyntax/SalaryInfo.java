package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int ARRAY_OF_DATA0 = 0;
    private static final int ARRAY_OF_DATA1 = 1;
    private static final int ARRAY_OF_DATA2 = 2;
    private static final int ARRAY_OF_DATA3 = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] arrayOfData;
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                arrayOfData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayOfData[ARRAY_OF_DATA0], FORMATTER);
                if (localDateDateFrom.compareTo(currentDate) <= 0
                        && localDateDateTo.compareTo(currentDate) >= 0
                        && arrayOfData[ARRAY_OF_DATA1].equals(name)) {
                    salary += (Integer.parseInt(arrayOfData[ARRAY_OF_DATA3]))
                            * Integer.parseInt(arrayOfData[ARRAY_OF_DATA2]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
