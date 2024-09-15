package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMATTER = "dd.MM.yyyy";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER);
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_COUNT_INDEX = 2;
    private static final int MONEY_AMOUNT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate start;
        LocalDate finish;
        LocalDate specificDate;
        String separator = System.lineSeparator();
        StringBuilder output = new StringBuilder();
        output.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(separator);
        for (String name : names) {
            int salary = 0;
            for (String element : data) {
                String[] splittedData = element.split(" ");
                start = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern(FORMATTER));
                finish = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern(FORMATTER));
                specificDate = LocalDate.parse(splittedData[DATE_INDEX],
                        DateTimeFormatter.ofPattern(FORMATTER));
                if (name.equals(splittedData[NAME_INDEX])
                        && specificDate.isAfter(start) &&
                        specificDate.isBefore(finish.plusDays(1))){
                    salary += Integer.parseInt(splittedData[HOURS_COUNT_INDEX])
                            * Integer.parseInt(splittedData[MONEY_AMOUNT_INDEX]);
                }
            }
            output.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(separator);
        }
        return output.toString().trim();
    }
}
