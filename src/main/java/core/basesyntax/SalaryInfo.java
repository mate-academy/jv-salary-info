package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final String SPLITTER = " ";
    public static final String DASH = " - ";
    public static final String REPORT_TITLE = "Report for period ";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int INDEX_OF_DATE = 0;
    public static final int INDEX_OF_NAME = 1;
    public static final int INDEX_OF_HOUR = 2;
    public static final int INDEX_OF_SALARY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(REPORT_TITLE)
                .append(dateFrom).append(DASH).append(dateTo).append(System.lineSeparator());
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate dataDate;

        String[] splittedData;
        int salarySum = 0;

        for (String name : names) {
            for (String dataRow : data) {
                splittedData = dataRow.split(SPLITTER);

                if (splittedData[INDEX_OF_NAME].equals(name)) {
                    dataDate = LocalDate.parse(splittedData[INDEX_OF_DATE], formatter);
                    if ((dataDate.isEqual(fromDate) || dataDate.isAfter(fromDate))
                            && (dataDate.isEqual(toDate) || dataDate.isBefore(toDate))) {
                        salarySum +=
                                (Integer.parseInt(splittedData[INDEX_OF_HOUR])
                                        * Integer.parseInt(splittedData[INDEX_OF_SALARY]));
                    }
                }
            }
            stringBuilder.append(name)
                    .append(DASH)
                    .append(salarySum);

            stringBuilder.append(
                    name.equals(names[names.length - 1]) ? "" : System.lineSeparator());
            salarySum = 0;
        }
        return stringBuilder.toString();
    }
}
