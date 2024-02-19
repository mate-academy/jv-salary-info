package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static int NAME_INDEX = 1;
    private static int DATE_INDEX = 0;
    private static int DAYS_INDEX = 2;
    private static int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = convertToLocalDate(dateFrom);
        LocalDate dateToLocal = convertToLocalDate(dateTo);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period " + dateFrom + " - " + dateTo);
        int[] salaries = new int[names.length];
        int count = 0;
        int salary = 0;
        for (String name: names) {
            for (String element: data) {
                String[] splitedData = element.split(" ");
                if (name.equals(splitedData[NAME_INDEX])) {
                    LocalDate date = convertToLocalDate(splitedData[DATE_INDEX]);
                    if (checkDate(date, dateFromLocal, dateToLocal)) {
                        salary += Integer.parseInt(splitedData[DAYS_INDEX])
                                * Integer.parseInt((splitedData[SALARY_INDEX]));
                        salaries[count] = salary;

                    }
                }
            }
            count++;
            salary = 0;
        }
        int salaryIndex = 0;
        for (String name: names) {
            builder.append(System.lineSeparator() + name + " - "
                    + String.valueOf(salaries[salaryIndex]));
            salaryIndex++;
        }
        return builder.toString();
    }

    private boolean checkDate(LocalDate dateToCheck, LocalDate dateFrom, LocalDate dateTo) {
        return dateToCheck.isAfter(dateFrom) && dateToCheck.isBefore(dateTo)
                || dateToCheck.equals(dateFrom) || dateToCheck.equals(dateTo);
    }

    private LocalDate convertToLocalDate(String date) {
        String[] dateFromValues = date.split("\\.");
        LocalDate convertedDate = LocalDate.of(Integer.parseInt(dateFromValues[2]),
                Integer.parseInt(dateFromValues[1]), Integer.parseInt(dateFromValues[0]));
        return convertedDate;
    }

}
