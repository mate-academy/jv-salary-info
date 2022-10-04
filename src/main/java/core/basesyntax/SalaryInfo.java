package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int PER_HOUR_INDEX = 3;
    private static final String SEPARATOR = " ";
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;

            for (String eachData : data) {
                String[] info = eachData.split(SEPARATOR);

                if (!info[NAME_INDEX].equals(name)) {
                    continue;
                } else if ((LocalDate.parse(info[DATE_INDEX], format)
                        .compareTo(LocalDate.parse(dateTo, format))) > 0
                        || (LocalDate.parse(info[DATE_INDEX], format)
                                .compareTo(LocalDate.parse(dateFrom, format)) < 0)) {
                    continue;
                } else {
                    salary += Integer.parseInt(info[HOURS_INDEX])
                            * Integer.parseInt(info[PER_HOUR_INDEX]);
                }
            }

            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return salaryInfo.toString();
    }
}
