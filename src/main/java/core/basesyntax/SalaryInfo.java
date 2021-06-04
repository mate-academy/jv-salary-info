package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int PARTICULAR_DAY_INDEX = 3;
    private static final int DATA_INDEX = 0;
    private static final int NAME_INDEX = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] splitArr = data[i].split(" ");
                LocalDate workingDayDate = LocalDate.parse(splitArr[DATA_INDEX],
                        DATE_TIME_FORMATTER);
                if (splitArr[NAME_INDEX].equals(name)
                        && compareDates(workingDayDate, dateFrom, dateTo)) {
                    salary += (Integer.parseInt(splitArr[WORKING_HOUR_INDEX])
                                    * Integer.parseInt(splitArr[PARTICULAR_DAY_INDEX]));
                }
            }
            stringBuilder.append("\n").append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }

    private boolean compareDates(LocalDate particularDay, String startDate, String endDate) {
        LocalDate dateFromFormatter = LocalDate.parse(startDate, DATE_TIME_FORMATTER);
        LocalDate dateToFormatter = LocalDate.parse(endDate, DATE_TIME_FORMATTER);
        return particularDay.compareTo(dateFromFormatter) >= 0
                && particularDay.compareTo(dateToFormatter) <= 0;
    }
}

