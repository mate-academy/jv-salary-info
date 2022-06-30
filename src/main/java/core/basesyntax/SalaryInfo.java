package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static class Data {
        private static final int date = 0;
        private static final int name = 1;
        private static final int workingHours = 2;
        private static final int incomePerHour = 3;
    }

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
                LocalDate currentDate = LocalDate.parse(arrayInfo[Data.date], FORMATTER);
                if (localDateFrom.compareTo(currentDate) <= 0
                        && localDateTo.compareTo(currentDate) >= 0
                        && arrayInfo[Data.name].equals(name)) {
                    salary += (Integer.parseInt(arrayInfo[Data.workingHours])
                            * Integer.parseInt(arrayInfo[Data.incomePerHour]));
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return salaryInfo.toString();
    }
}
