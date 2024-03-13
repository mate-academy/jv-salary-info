package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatterYears = DateTimeFormatter.ofPattern("yyyy");
        DateTimeFormatter dateTimeFormatterMonths = DateTimeFormatter.ofPattern("MM");
        DateTimeFormatter dateTimeFormatterDays = DateTimeFormatter.ofPattern("dd");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);

        LocalDate localDateFrom = LocalDate.of(
                Integer.parseInt(dateFrom.substring(6, 10)),
                Integer.parseInt(dateFrom.substring(3, 5)),
                Integer.parseInt(dateFrom.substring(0, 2))
        );
        LocalDate localDateTo = LocalDate.of(
                Integer.parseInt(dateTo.substring(6, 10)),
                Integer.parseInt(dateTo.substring(3, 5)),
                Integer.parseInt(dateTo.substring(0, 2))
        );

        int dataIndex = 0;
        int nameIndex = 1;
        int hoursIndex = 2;
        int salaryPerHourIndex = 3;

        for (String name : names) {
            int salary = 0;
            for (String oneData : data) {
                String[] oneDataSplit = oneData.split(" ");
                LocalDate localOneData = LocalDate.of(
                        Integer.parseInt(oneDataSplit[dataIndex].substring(6, 10)),
                        Integer.parseInt(oneDataSplit[dataIndex].substring(3, 5)),
                        Integer.parseInt(oneDataSplit[dataIndex].substring(0, 2))
                );
                String trimmedName = oneDataSplit[nameIndex];

                if (localOneData.isAfter(localDateFrom) && localOneData.isBefore(localDateTo)
                        || localOneData.equals(localDateTo) || localOneData.equals(localDateFrom)) {

                    if (trimmedName.equals(name)) {
                        int hoursInData = Integer.parseInt(oneDataSplit[hoursIndex]);
                        int salaryPerHour = Integer.parseInt(oneDataSplit[salaryPerHourIndex]);

                        salary += hoursInData * salaryPerHour;
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
