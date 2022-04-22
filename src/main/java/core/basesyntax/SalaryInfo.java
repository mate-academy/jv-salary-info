package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateStartWork = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateFinishWork = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        String delimiter = " ";
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] str = data[j].split(delimiter);
                LocalDate workDay = LocalDate.parse(str[0], dateTimeFormatter);
                if (workDay.isAfter(dateStartWork.minusDays(1))
                        && workDay.isBefore(dateFinishWork.plusDays(1))
                        && names[i].equals(str[1])) {
                    salary += Integer.parseInt(str[2]) * Integer.parseInt(str[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salary);
            salary = 0;
        }
        return stringBuilder.toString();
    }
}

