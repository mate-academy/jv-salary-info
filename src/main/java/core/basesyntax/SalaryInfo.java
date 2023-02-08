package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] tempData;
        int salarySumm;
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        try {
            LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
            LocalDate secondDate = LocalDate.parse(dateTo, FORMATTER);
            LocalDate workDate = LocalDate.now();
            for (int i = 0; i < names.length; i++) {
                result.append(System.lineSeparator());
                salarySumm = 0;
                for (int j = 0; j < data.length; j++) {
                    tempData = data[j].split(" ");
                    workDate = LocalDate.parse(tempData[0], FORMATTER);
                    if (names[i].equals(tempData[1])
                            && workDate.compareTo(firstDate) >= 0
                            && workDate.compareTo(secondDate) <= 0) {
                        salarySumm = salarySumm
                            + Integer.parseInt(tempData[2]) * Integer.parseInt(tempData[3]);
                    }
                }
                result.append(names[i]).append(" - ").append(salarySumm);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Incorrect data");
        }
        return result.toString();
    }
}
