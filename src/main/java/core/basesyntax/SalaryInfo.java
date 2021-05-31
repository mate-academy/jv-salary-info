package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dateStart = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate dateEnd = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder salaryFinal = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] usersInformation = data[j].split(" ");
                LocalDate localDate = LocalDate.parse(usersInformation[0], DATE_TIME_FORMATTER);
                if ((localDate.equals(dateStart) || localDate.equals(dateEnd)
                        || localDate.isAfter(dateStart) && localDate.isBefore(dateEnd))
                        && usersInformation[1].equals(names[i])) {

                    salary += Integer.parseInt(usersInformation[2])
                            * Integer.parseInt(usersInformation[3]);
                }
            }
            salaryFinal.append("\n").append(names[i]).append(" - ").append(salary);
        }
        return "Report for period " + dateFrom + " - " + dateTo + salaryFinal;
    }
}
