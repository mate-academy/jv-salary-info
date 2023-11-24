package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int PARTICULAR_DAY = 0;
    private static final int NAME_OF_EMPLOYEE = 1;
    private static final int WORKING_HOUR = 2;
    private static final int INCOME_PER_HOUR = 3;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dtFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dtTo = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        int salaryPerMonth = 0;
        for (String name : names) {
            for (String info : data) {
                String[] infoSpl = info.split(" ");
                LocalDate ptclDay = LocalDate.parse(infoSpl[PARTICULAR_DAY], formatter);
                if (infoSpl[NAME_OF_EMPLOYEE].equals(name)
                        && ptclDay.isAfter(dtFrom) && ptclDay.isBefore(dtTo)
                        || (infoSpl[NAME_OF_EMPLOYEE].equals(name) && ptclDay.isEqual(dtFrom))
                        || (infoSpl[NAME_OF_EMPLOYEE].equals(name) && ptclDay.isEqual(dtTo))) {
                    salaryPerMonth += Integer.parseInt(infoSpl[WORKING_HOUR])
                            * Integer.parseInt(infoSpl[INCOME_PER_HOUR]);
                }
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salaryPerMonth);
            salaryPerMonth = 0;
        }
        return builder.toString();
    }
}

