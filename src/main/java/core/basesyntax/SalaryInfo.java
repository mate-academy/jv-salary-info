package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    static final int FIRST_INDEX = 0;
    static final int SECOND_INDEX = 1;
    static final int THIRD_INDEX = 2;
    static final int FOURTH_INDEX = 3;
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] salaries = new String[names.length];
        int index = 0;
        int income = 0;
        LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate finishDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                String[] personalData = data[i].split(" ");
                LocalDate currentDate = LocalDate.parse(personalData[FIRST_INDEX], FORMATTER);
                if (name.equals(personalData[SECOND_INDEX]) && !currentDate.isAfter(finishDate)
                        && !currentDate.isBefore(startDate)) {
                    income += Integer.parseInt(personalData[THIRD_INDEX])
                            * Integer.parseInt(personalData[FOURTH_INDEX]);
                }
            }
            salaries[index] = new StringBuilder().append(name).append(" - ")
                    .append(income).toString();
            index++;
            income = 0;
        }
        return getInfo(salaries, dateFrom, dateTo);
    }

    public String getInfo(String[] salaries, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(Arrays.toString(salaries)
                        .replace(",", System.lineSeparator()).replace("[", System.lineSeparator())
                        .replace("]", "").replace(" ", "").replace("-", " - "));
        return result.toString();
    }
}
