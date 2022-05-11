package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int START_INDEX = 0;
    private static final int FIRST_ELEMENT_INDEX = 1;
    private static final int PENULTIMATE_INDEX = 2;
    private static final int LAST_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] dataArray;
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                dataArray = line.split(" ");
                LocalDate currentDate = LocalDate.parse(dataArray[START_INDEX], FORMATTER);
                if ((localDateFrom.isBefore(currentDate) || localDateFrom.isEqual(currentDate))
                        && (localDateTo.isAfter(currentDate) || localDateTo.isEqual(currentDate))
                        && dataArray[FIRST_ELEMENT_INDEX].equals(name)) {
                    salary += Integer.parseInt(dataArray[LAST_INDEX])
                            * Integer.parseInt(dataArray[PENULTIMATE_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
