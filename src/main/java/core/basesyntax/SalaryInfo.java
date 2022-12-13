package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private String[] dataToStringArray;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        int salary = 0;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                dataToStringArray = data[j].split(" ");
                if (getLocalDate(dataToStringArray[0]).compareTo(getLocalDate(dateFrom)) >= 0
                        && getLocalDate(dataToStringArray[0]).compareTo(getLocalDate(dateTo)) <= 0
                        && data[j].contains(names[i])) {
                    salary += Integer.parseInt(dataToStringArray[2])
                            * Integer.parseInt(dataToStringArray[3]);
                }
            }
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary);
            salary = 0;
        }
        String resultString = result.toString();
        return resultString;
    }

    public LocalDate getLocalDate(String inputDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate parsedDate = LocalDate.parse(inputDate, formatter);
            return parsedDate;
        } catch (DateTimeParseException e) {
            System.out.printf("%s is not parsable!%n", inputDate);
        }
        return LocalDate.parse(inputDate);
    }
}
