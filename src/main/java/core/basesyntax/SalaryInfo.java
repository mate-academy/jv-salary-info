package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final int dataIndex = 0;
    private final int nameIndex = 1;
    private final int indexOfHour = 2;
    private final int salaryIndex = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int salaryForPeriod = 0;
            for (String eachData : data) {
                String[] dataNameSalary = eachData.split(" ");
                final String employerName = dataNameSalary[nameIndex];
                final int hoursOfWork = Integer.parseInt(dataNameSalary[indexOfHour]);
                final int salaryPerHour = Integer.parseInt(dataNameSalary[salaryIndex]);
                LocalDate localDateOfWork = LocalDate.parse(dataNameSalary[dataIndex], formatter);
                if (name.equals(employerName)
                            && (localDateOfWork.isAfter(localDateFrom)
                            && localDateOfWork.isBefore(localDateTo)
                            || localDateOfWork.isEqual(localDateFrom)
                            || localDateOfWork.isEqual(localDateTo))) {
                    salaryForPeriod += hoursOfWork * salaryPerHour;
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaryForPeriod);
        }
        return builder.toString();
    }
}
