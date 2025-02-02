package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int daysNumber = 0;
        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        //builder.append(System.lineSeparator());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] employeeDetails = data[j].split(" ");
                LocalDate thisDate = LocalDate.parse(employeeDetails[0], formatter);
                if (names[i].equals(employeeDetails[1])
                        && checkDate(thisDate, startDate, endDate)) {
                    salary += Integer.parseInt(employeeDetails[2])
                            * Integer.parseInt(employeeDetails[3]);
                }
            }
            builder.append(System.lineSeparator()).append(names[i] + " - ").append(salary);
        }
        return builder.toString();
    }

    boolean checkDate(LocalDate currentDate, LocalDate startDate, LocalDate endDate) {
        if ((currentDate.isAfter(startDate) && currentDate.isBefore(endDate))
                || currentDate.equals(startDate) || currentDate.equals(endDate)) {
            return true;
        }
        return false;
    }
}
