package core.basesyntax;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_POSITION_IN_ARRAY = 0;
    public static final int NAME_POSITION_IN_ARRAY = 1;
    public static final int HOURS_POSITION_IN_ARRAY = 2;
    public static final int HOURLY_RATE_POSITION_IN_ARRAY = 3;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate toDate = parse(dateTo,formatter);
        LocalDate fromDate = parse(dateFrom,formatter);
        String[] nameArray = new String[names.length];
        Integer[] salaryArray = new Integer[names.length];
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            salaryArray[i] = 0;
            nameArray[i] = name;
            for (String info : data) {
                String[] parts = info.split(" ");
                LocalDate actualDate = parse(parts[DATE_POSITION_IN_ARRAY],formatter);
                if (parts[NAME_POSITION_IN_ARRAY].equals(name)
                        && (actualDate.isBefore(toDate) || actualDate.isEqual(toDate))
                        && (actualDate.isAfter(fromDate) || actualDate.isEqual(fromDate))) {
                    salaryArray[i] += Integer.parseInt(parts[HOURS_POSITION_IN_ARRAY])
                            * Integer.parseInt(parts[HOURLY_RATE_POSITION_IN_ARRAY]);
                }
            }
            report.append(nameArray[i]).append(" - ").append(salaryArray[i]);
            if (i != (names.length - 1)) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }
}
