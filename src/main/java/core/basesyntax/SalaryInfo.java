package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOUR = 2;
    private static final int INDEX_SALARY_PER_HOUR = 3;
    private static final String FORMAT_DATE = "dd.MM.yyyy";
    private static final String DELIMITER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            sb.append(System.lineSeparator()).append(name).append(" - ")
                    .append(getInfoSalaryForEachName(name, data, dateFrom, dateTo));
        }
        return sb.toString();
    }

    private int getInfoSalaryForEachName(String name, String[] data,
                                         String dateFrom, String dateTo) {
        LocalDate localDateFrom = getDate(dateFrom);
        LocalDate localDateTo = getDate(dateTo);
        int salary = 0;
        for (String datum : data) {
            String[] info = getInfoFromString(datum);
            LocalDate dataFromString = getDate(info[INDEX_DATE]);
            if ((dataFromString.isAfter(localDateFrom)
                    || dataFromString.isEqual(localDateFrom))
                    && (dataFromString.isBefore(localDateTo)
                    || dataFromString.isEqual(localDateTo))) {
                if (name.equals(info[INDEX_NAME])) {
                    salary += Integer.parseInt(info[INDEX_HOUR])
                            * Integer.parseInt(info[INDEX_SALARY_PER_HOUR]);
                }
            }
        }
        return salary;
    }

    private String[] getInfoFromString(String data) {
        return data.split(DELIMITER);
    }

    private LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        return LocalDate.parse(date, formatter);
    }

}
