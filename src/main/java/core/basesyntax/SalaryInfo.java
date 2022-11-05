package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int TARIFF_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                                                    .append(dateFrom).append(" - ")
                                                    .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (String info : data) {
                String[] splitedInfo = info.split(" ");
                if (names[i].equals(splitedInfo[NAME_INDEX])
                        && compareDates(splitedInfo[DATE_INDEX], dateFrom, dateTo)) {
                    salary += (Integer.parseInt(splitedInfo[HOURS_INDEX])
                            * Integer.parseInt(splitedInfo[TARIFF_INDEX]));
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
        }
        return builder.toString();
    }

    private boolean compareDates(String workDate, String dateFrom, String dateTo) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localWorkDate = LocalDate.parse(workDate, dateTimeFormatter);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        return localWorkDate.equals(localDateFrom)
                || localWorkDate.isAfter(localDateFrom) && localWorkDate.isBefore(localDateTo)
                || localWorkDate.equals(localDateTo) ? true : false;
    }
}
