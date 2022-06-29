package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        String[] arrayInfo;

        for (String name : names) {
            int salary = 0;
            for (String info : data) {
                arrayInfo = info.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayInfo[0], FORMATTER);
                if (localDateFrom.compareTo(currentDate) <= 0
                        && localDateTo.compareTo(currentDate) >= 0
                        && arrayInfo[1].equals(name)) {
                    salary += (Integer.parseInt(arrayInfo[2])
                            * Integer.parseInt(arrayInfo[3]));
                }
            }
            salaryInfo.append("\n").append(name).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}
