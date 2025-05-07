package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = stringToDate(dateFrom);
        LocalDate localDateTo = stringToDate(dateTo);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String info : data) {
                String[] splitOneInfo = info.split(" ");
                LocalDate currentDate = stringToDate(splitOneInfo[0]);
                if (name.equals(splitOneInfo[1])
                        && !currentDate.isBefore(localDateFrom)
                        && !currentDate.isAfter(localDateTo)) {
                    salary += Integer.parseInt(splitOneInfo[2])
                            * Integer.parseInt(splitOneInfo[3]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }

    private static LocalDate stringToDate(String date) {
        return LocalDate.parse(date, FORMATER);
    }
}
