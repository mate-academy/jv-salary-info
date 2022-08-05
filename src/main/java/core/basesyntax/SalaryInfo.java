package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] dataList, String dataFrom, String dateTo) {
        LocalDate datesFrom = LocalDate.parse(dataFrom, DATE_FORMATTER);
        LocalDate datesTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dataFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int sumOfSalary = 0;
            for (String data : dataList) {
                String[] dataSplit = data.split(" ");
                LocalDate currentData = LocalDate.parse(dataSplit[DATE_INDEX], DATE_FORMATTER);
                if ((currentData.equals(datesFrom) || currentData.isAfter(datesFrom))
                        && (currentData.equals(datesTo) || currentData.isBefore(datesTo))
                        && dataSplit[NAME_INDEX].equals(name)) {
                    sumOfSalary += Integer.valueOf(dataSplit[HOUR_INDEX])
                            * Integer.valueOf(dataSplit[SALARY_PER_HOUR_INDEX]);

                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(sumOfSalary);
        }
        return builder.toString();
    }
}
