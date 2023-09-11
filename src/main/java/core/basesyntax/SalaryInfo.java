package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMATTER = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = dateFormat(dateFrom);
        LocalDate toDate = dateFormat(dateTo);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate thisDate;
        for (String name :
                names) {
            int sum = 0;
            builder.append(System.getProperty("line.separator"))
                    .append(name)
                    .append(" - ");
            for (String dat :
                    data) {
                if (dat.isEmpty()) {
                    continue;
                } else {
                    String[] array = dat.split(" ");
                    thisDate = dateFormat(array[0]);
                    if (thisDate.isAfter(fromDate.minusDays(1))
                            && thisDate.isBefore(toDate.plusDays(1))
                            && name.equals(array[1])) {
                        sum += (Integer.parseInt(array[2]) * Integer.parseInt(array[3]));
                    }
                }
            }
            builder.append(sum);
        }
        return builder.toString();
    }

    public static LocalDate dateFormat(String thisDate) {
        return LocalDate.parse(thisDate, DateTimeFormatter.ofPattern(FORMATTER));
    }
}
