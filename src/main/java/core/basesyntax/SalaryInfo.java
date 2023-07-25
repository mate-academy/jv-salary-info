package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salaryEmployee = 0;
        LocalDate dateF = LocalDate.parse(dateFrom,formatter);
        LocalDate dateT = LocalDate.parse(dateTo, formatter);
        StringBuilder resultString = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] splitMassiv = data[j].split(" ");
                if (LocalDate.parse(splitMassiv[0],formatter).isBefore(dateT.plusDays(1))
                        && LocalDate.parse(splitMassiv[0], formatter).isAfter(dateF.minusDays(1))
                        && splitMassiv[1].equals(names[i])) {
                    salaryEmployee += Integer.parseInt(splitMassiv[2])
                            * Integer.parseInt(splitMassiv[3]);
                }
            }
            resultString.append(System.lineSeparator())
                    .append(names[i]).append(" - ").append(salaryEmployee);
            salaryEmployee = 0;
        }
        return resultString.toString();
    }
}
