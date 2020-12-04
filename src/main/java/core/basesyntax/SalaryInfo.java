package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, DATE_FORMAT);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int personalSalary = 0;
            for (String employeeData : data) {
                String[] splitedData = employeeData.split(" ");
                LocalDate employeeWorkDate = LocalDate.parse(splitedData[0], DATE_FORMAT);
                if (!employeeWorkDate.isBefore(formattedDateFrom)
                        && !employeeWorkDate.isAfter(formattedDateTo)) {
                    if (splitedData[1].equals(name)) {
                        personalSalary += Integer.parseInt(splitedData[2])
                                * Integer.parseInt(splitedData[3]);
                    }
                }
            }
            stringBuilder.append("\n").append(name).append(" - ").append(personalSalary);
        }
        return stringBuilder.toString();
    }
}

