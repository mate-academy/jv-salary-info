package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names,
                                       String[] data,
                                       String dateFrom,
                                       String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int totalSalary = 0;
            for (String dat : data) {
                String[] split = dat.split(" ");
                LocalDate localDateFact = LocalDate.parse(split[0], FORMATTER);
                int workingHour = Integer.parseInt(split[2]);
                int incomePerHour = Integer.parseInt(split[3]);
                String localName = split[1];
                if ((localDateFact.equals(localDateFrom)
                        || localDateFact.equals(localDateTo)
                        || localDateFact.isAfter(localDateFrom)
                        && localDateFact.isBefore(localDateTo))
                        && localName.equals(name)) {
                    totalSalary += workingHour * incomePerHour;
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return builder.toString();
    }
}
