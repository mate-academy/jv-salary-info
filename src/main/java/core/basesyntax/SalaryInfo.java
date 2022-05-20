package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        long localDateFrom = LocalDate.parse(dateFrom, formatter).toEpochDay();
        long localDateTo = LocalDate.parse(dateTo, formatter).toEpochDay();
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int employeesSalary = 0;
            for (String dataInfo: data) {
                String[] dataSplitted = dataInfo.split(" ");
                long localDateNow = LocalDate.parse(dataSplitted[0], formatter).toEpochDay();
                if (name.equals(dataSplitted[1])
                        && (localDateNow >= localDateFrom && localDateNow <= localDateTo)) {
                    employeesSalary += Integer.parseInt(dataSplitted[2])
                                    * Integer.parseInt(dataSplitted[3]);
                }
            }
            builder.append(System.lineSeparator()).append(name)
                   .append(" - ").append(employeesSalary);
        }
        return builder.toString();
    }
}
