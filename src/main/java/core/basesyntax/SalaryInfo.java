package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);

        int dataIndex = 0;
        int nameIndex = 1;
        int hoursIndex = 2;
        int salaryPerHourIndex = 3;

        for (String name : names) {
            int salary = 0;
            for (String entry : data) {
                String[] entrySplit = entry.split(" ");
                LocalDate localOneData = LocalDate.parse(entrySplit[dataIndex], FORMATTER);;
                String trimmedName = entrySplit[nameIndex];

                if (localOneData.isAfter(localDateFrom) && localOneData.isBefore(localDateTo)
                        || localOneData.equals(localDateTo) || localOneData.equals(localDateFrom)) {

                    if (trimmedName.equals(name)) {
                        int hoursInData = Integer.parseInt(entrySplit[hoursIndex]);
                        int salaryPerHour = Integer.parseInt(entrySplit[salaryPerHourIndex]);

                        salary += hoursInData * salaryPerHour;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
