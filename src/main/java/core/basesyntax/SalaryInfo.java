package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SEPARATOR = " - ";
    private static final String SPACE = " ";
    private static final DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NEXT_DAY = 1;
    private static final int DATE_OF_WORK = 0;
    private static final int NAME_OF_EMPLOYER = 1;
    private static final int TIME_OF_WORK = 2;
    private static final int EARN_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        int[] empolyerSalaryPeriod = new int[names.length];
        String[] dataUser;
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATER);
        LocalDate lastDate = LocalDate.parse(dateTo, FORMATER);
        LocalDate currentDate;
        builder.append("Report for period ").append(dateFrom)
                .append(SEPARATOR).append(dateTo);
        for (int i = 0; i < names.length; i++) {
            for (String rowData : data) {
                dataUser = rowData.split(SPACE);
                currentDate = LocalDate.parse(dataUser[DATE_OF_WORK], FORMATER);
                if (currentDate.isAfter(firstDate)
                        && currentDate.isBefore(lastDate.plusDays(NEXT_DAY))) {
                    if (names[i].equals(dataUser[NAME_OF_EMPLOYER])) {
                        empolyerSalaryPeriod[i] += Integer.parseInt(dataUser[TIME_OF_WORK])
                                * Integer.parseInt(dataUser[EARN_PER_HOUR]);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(SEPARATOR)
                    .append(empolyerSalaryPeriod[i]);
        }
        return builder.toString();
    }
}
