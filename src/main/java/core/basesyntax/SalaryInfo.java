package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int WORKED_INDEX = 2;
    public static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        if (data.length < 1 || names.length < 1) {
            return null;
        }
        StringBuilder resultString = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        int[] totalSalaryList = new int[names.length];
        List<String> namesList = new ArrayList<>(Arrays.asList(names));
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        for (String employeeData : data) {
            String[] tokensData = employeeData.split(" ");
            String dateStr = tokensData[DATE_INDEX];

            String nameEmployer = tokensData[NAME_INDEX];
            int hoursWorked = Integer.parseInt(tokensData[WORKED_INDEX]);
            int hourlyRate = Integer.parseInt(tokensData[RATE_INDEX]);
            LocalDate dateToCheck = LocalDate.parse(dateStr, formatter);

            if (dateToCheck.isAfter(fromDate) && dateToCheck.isBefore(toDate)
                    || dateToCheck.isEqual(fromDate) || dateToCheck.isEqual(toDate)) {
                for (String employeeName : names) {
                    if (nameEmployer.equals(employeeName)) {
                        int salary = hoursWorked * hourlyRate;
                        totalSalaryList[namesList.indexOf(nameEmployer)] += salary;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < names.length; ++i) {
            resultString.append(System.lineSeparator())
                    .append(namesList.get(i)).append(" - ").append(totalSalaryList[i]);
        }

        return resultString.toString();
    }
}
