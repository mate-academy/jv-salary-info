package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int indexOfArray = 0;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate newDateFrom = getParseDate(dateFrom);
        LocalDate newDateTo = getParseDate(dateTo);

        if (!dateFrom.equals(dateTo)) {
            newDateTo = newDateTo.plusDays(1);
        }

        for (int i = 0; i < names.length; i++) {
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] tempArray = data[j].split(" ");
                LocalDate employeeDate = getParseDate(tempArray[indexOfArray]);
                if (names[i].equals(tempArray[indexOfArray + 1])) {
                    if (employeeDate.isAfter(newDateFrom)
                            && employeeDate.isBefore(newDateTo)) {
                        salary += Integer.parseInt(tempArray[indexOfArray + 2])
                                * Integer.parseInt(tempArray[indexOfArray + 3]);
                    }
                }
            }
            stringBuilder.append("\n")
                    .append(names[i])
                    .append(" - ")
                    .append(salary);
        }
        return stringBuilder.toString();
    }

    private LocalDate getParseDate(String dateFrom) {
        return LocalDate.parse(dateFrom, formatter);
    }
}
