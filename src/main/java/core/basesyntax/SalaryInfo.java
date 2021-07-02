package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = stringToDate(dateFrom);
        LocalDate to = stringToDate(dateTo);
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String info : data) {
                String[] splitOneInfo = info.split(" ");
                LocalDate currentDate = stringToDate(splitOneInfo[0]);
                if (name.equals(splitOneInfo[1])
                        && !currentDate.isBefore(from)
                        && !currentDate.isAfter(to)) {
                    salary += Integer.parseInt(splitOneInfo[2])
                            * Integer.parseInt(splitOneInfo[3]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }

    private LocalDate stringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }
}


