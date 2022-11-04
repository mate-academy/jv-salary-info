package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int indexOfDate = 0;
        final int indexOfName = 1;
        final int indexOfHours = 2;
        final int indexOfTariff = 3;
        StringBuilder builder = new StringBuilder("Report for period ")
                                                    .append(dateFrom).append(" - ")
                                                    .append(dateTo);
        int salary;
        for (int i = 0; i < names.length; i++) {
            salary = 0;
            for (String info : data) {
                String[] splitedInfo = info.split(" ");
                if (names[i].equals(splitedInfo[indexOfName])
                        && compareDates(splitedInfo[indexOfDate], dateFrom, dateTo)) {
                    salary += (Integer.parseInt(splitedInfo[indexOfHours])
                            * Integer.parseInt(splitedInfo[indexOfTariff]));
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
        }
        return builder.toString();
    }

    private boolean compareDates(String workDate, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localWorkDate = LocalDate.parse(workDate, dateTimeFormatter);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        return localWorkDate.equals(localDateFrom)
                || localWorkDate.isAfter(localDateFrom) && localWorkDate.isBefore(localDateTo)
                || localWorkDate.equals(localDateTo) ? true : false;
    }
}
