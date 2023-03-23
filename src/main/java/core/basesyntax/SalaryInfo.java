package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        LocalDate splitDate;
        int salary;
        String[] splitData;
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        for (String name : names) {
            salary = 0;
            for (String dataInfo : data) {
                splitData = dataInfo.split(" ");
                splitDate = LocalDate.parse(splitData[0], DATE_FORMATTER);
                if (name.equals(splitData[1])
                        && (splitDate.isAfter(localDateFrom)
                        || splitDate.isEqual(localDateFrom))
                        && (splitDate.isBefore(localDateTo)
                        || splitDate.isEqual(localDateTo))) {       
                    salary += Integer.parseInt(splitData[2])
                        * Integer.parseInt(splitData[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
