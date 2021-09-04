package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    public static String getSalaryInfo(String[] names,
                                       String[] data,
                                       String dateFrom,
                                       String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        int johnSalary = 0;
        int andrewSalary = 0;
        int kateSalary = 0;
        for (String dat : data) {
            LocalDate localDateFact = LocalDate.parse(dat.split(" ")[0], FORMATTER);
            int workingHour = Integer.parseInt(dat.split(" ")[2]);
            int incomePerHour = Integer.parseInt(dat.split(" ")[3]);
            if (dat.split(" ")[1].equals(names[0])
                    && localDateFact.isAfter(localDateFrom)
                    && localDateFact.isBefore(localDateTo)
                    || localDateFact.isEqual(localDateFrom)
                    || localDateFact.isEqual(localDateTo)) {
                johnSalary += workingHour * incomePerHour;
            } else if (dat.split(" ")[1].equals(names[1])
                    && localDateFact.isAfter(localDateFrom)
                    && localDateFact.isBefore(localDateTo)
                    || localDateFact.isEqual(localDateFrom)
                    || localDateFact.isEqual(localDateTo)) {
                andrewSalary += workingHour * incomePerHour;
            } else if (dat.split(" ")[1].equals(names[2])
                    && localDateFact.isAfter(localDateFrom)
                    && localDateFact.isBefore(localDateTo)
                    || localDateFact.isEqual(localDateFrom)
                    || localDateFact.isEqual(localDateTo)) {
                kateSalary += workingHour * incomePerHour;
            }
        }
        return builder.append("Report for period ")
                .append(localDateFrom)
                .append(" - ")
                .append(localDateTo)
                .append(System.lineSeparator())
                .append("John")
                .append(" - ")
                .append(johnSalary)
                .append(System.lineSeparator())
                .append("Andrew")
                .append(" - ")
                .append(andrewSalary)
                .append(System.lineSeparator())
                .append("Kate")
                .append(" - ")
                .append(kateSalary)
                .toString();
    }
}








