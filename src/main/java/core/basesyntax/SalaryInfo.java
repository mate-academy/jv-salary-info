package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final DateTimeFormatter TIME_FORMATER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private LocalDate localDateFrom;
    private LocalDate localDateTo;
    private LocalDate localDateData;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            localDateFrom = LocalDate.parse(dateFrom, TIME_FORMATER);
            localDateTo = LocalDate.parse(dateTo, TIME_FORMATER);
        } catch (DateTimeParseException exc) {
            throw exc;
        }
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);
        long[] countSalary = new long[names.length];
        for (int i = 0; i < names.length; i++){
            for(int j = 0; j < data.length; j++){
                String[] lineOfDate = data[j].split(" ");
                try {
                    localDateData = LocalDate.parse(lineOfDate[0],TIME_FORMATER);
                } catch (DateTimeParseException exc) {
                    throw exc;
                }
                if ((localDateData.isEqual(localDateFrom) || localDateData.isAfter(localDateFrom))
                        && (localDateData.isBefore(localDateTo) || localDateData.isEqual(localDateTo))
                        && names[i].equals(lineOfDate[1])) {
                    countSalary[i] += Long.parseLong(lineOfDate[2]) * Long.parseLong(lineOfDate[3]);
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(countSalary[i]);
        }
        return builder.toString();
    }
}