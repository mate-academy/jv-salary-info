package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom + " - " + dateTo);
        final int dayNumber = 0;
        final int monthNumber = 1;
        final int yearNumber = 2;
        String[] dateFromString = dateFrom.split("\\.");
        String[] dateToString = dateTo.split("\\.");
        LocalDate localDateFrom = LocalDate.of(Integer.valueOf(dateFromString[yearNumber]),
                Integer.valueOf(dateFromString[monthNumber]),
                Integer.valueOf(dateFromString[dayNumber]));
        LocalDate localDateTo = LocalDate.of(Integer.valueOf(dateToString[yearNumber]),
                Integer.valueOf(dateToString[monthNumber]),
                Integer.valueOf(dateToString[dayNumber]));
        LocalDate workingDate;
        String[] workingDateToString;
        String[] employerInfo;
        final int workingDateNumber = 0;
        final int nameNumber = 1;
        final int workHoursPerDayNumber = 2;
        final int priceForOneWorkHour = 3;
        int[] employersSalary = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            employerInfo = data[i].split(" ");
            workingDateToString = employerInfo[workingDateNumber].split("\\.");
            workingDate =
                    LocalDate.of(Integer.valueOf(workingDateToString[yearNumber]),
                    Integer.valueOf(workingDateToString[monthNumber]),
                            Integer.valueOf(workingDateToString[dayNumber]));
            for (int j = 0; j < names.length; j++) {
                if (employerInfo[nameNumber].equals(names[j])
                        && (workingDate.isAfter(localDateFrom)
                        && workingDate.isBefore(localDateTo) || workingDate.equals(localDateTo)
                        || workingDate.equals(localDateFrom))) {
                    employersSalary[j] += Integer.valueOf(employerInfo[priceForOneWorkHour])
                            * Integer.valueOf(employerInfo[workHoursPerDayNumber]);
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(names[i] + " - " + employersSalary[i]);
        }
        return stringBuilder.toString();
    }
}
