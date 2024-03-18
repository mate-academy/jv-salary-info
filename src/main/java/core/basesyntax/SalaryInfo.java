package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
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
            for (String entry : data) {
                String[] entrySplit = entry.split(" ");
                LocalDate localOneData = LocalDate.of(
                        Integer.parseInt(entrySplit[dataIndex].substring(6, 10)),
                        Integer.parseInt(entrySplit[dataIndex].substring(3, 5)),
                        Integer.parseInt(entrySplit[dataIndex].substring(0, 2))
                );
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
