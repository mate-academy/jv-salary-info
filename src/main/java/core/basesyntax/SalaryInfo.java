package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] result = new int[names.length];
        LocalDate fromDate = LocalDate.parse(dateFrom, PATTERN);
        LocalDate toDate = LocalDate.parse(dateTo, PATTERN);
        int sumOfSalary = 0;
        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < data.length; i++) {
                StringBuilder stringBuilderData = new StringBuilder(Arrays
                        .toString(new String[]{data[i]}));
                String[] splitData = stringBuilderData.toString().split(" ");
                LocalDate date = LocalDate.parse(splitData[DATE_INDEX]
                        .substring(1), PATTERN);
                String employeeName = splitData[NAME_INDEX];
                if (names[j].equals(employeeName) && (!date.isAfter(toDate)
                        && !date.isBefore(fromDate))) {
                    sumOfSalary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]
                            .replaceAll("]", ""));
                }
                result[j] = sumOfSalary;
            }
        }
        StringBuilder report = new StringBuilder();
        String reportResult = null;
        for (int y = 0; y < result.length; y++) {
            reportResult = String.valueOf(report.append(System.lineSeparator()).append(names[y]).append(" - ")
                    .append(result[y]));
        }
        return "Report for period" + " " + dateFrom + "-" + dateTo + reportResult;
    }
}
