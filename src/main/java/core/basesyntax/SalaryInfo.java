package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, format);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, format);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            int salaryPerPeriod = 0;
            for (String newData : data) {
                String[] seperatedData = newData.split(" ");
                LocalDate dateTime = LocalDate.parse(seperatedData[0], format);
                if ((dateTime.isAfter(formattedDateFrom) && dateTime.isBefore(formattedDateTo)
                        || dateTime.equals(formattedDateFrom)
                        || dateTime.equals(formattedDateTo))
                        && name.equals(seperatedData[1])) {
                    int workingHuors = Integer.parseInt(seperatedData[2]);
                    int salaryPerHour = Integer.parseInt(seperatedData[3]);
                    salaryPerPeriod = salaryPerPeriod + (salaryPerHour * workingHuors);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaryPerPeriod);
        }
        return builder.toString();
    }
}
