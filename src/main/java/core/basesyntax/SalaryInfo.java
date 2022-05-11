package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] rowData;

        for (String name: names) {
            int salary = 0;
            for (String line : data) {
                rowData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(rowData[0], FORMATTER);
                if ((localDateFrom.isBefore(currentDate) || localDateFrom.isEqual(currentDate))
                        && (localDateTo.isAfter(currentDate) || localDateTo.isEqual(currentDate))
                        && rowData[1].equals(name)) {
                    salary += (Integer.parseInt(rowData[3])
                        * Integer.parseInt(rowData[2]));
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
