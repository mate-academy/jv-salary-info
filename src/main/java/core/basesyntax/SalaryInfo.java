package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int [] result = new int[names.length];
        LocalDate fromDate = LocalDate.parse(dateFrom, PATTERN);

        LocalDate toDate = LocalDate.parse(dateTo, PATTERN);
        for (int j = 0; j < names.length; j++) {
            int sumOfSalary = 0;
            for (int i = 0; i < data.length; i++) {
                StringBuilder stringBuilderData = new StringBuilder(Arrays
                        .toString(new String[]{data[i]}));
                String [] splitData = stringBuilderData.toString().split(" ");
                LocalDate date = LocalDate.parse(splitData[DATE_INDEX]
                        .substring(1), PATTERN);
                String employeeName = splitData[NAME_INDEX];
                if (names[j].equals(employeeName) && (date.isAfter(fromDate)
                        && (date.isBefore(toDate)))) {
                    sumOfSalary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]
                            .replaceAll("]", ""));
                }
                result[j] = sumOfSalary;
            }
        }
        return "Report for period" + " " + dateFrom + "-" + dateTo + "\n"
                + names[0] + " " + "-" + " " + result[0] + "\n"
                + names[1] + " " + "-" + " " + result[1] + "\n"
                + names[2] + " " + "-" + " " + result[2] + "\n";
    }
}
