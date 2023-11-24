package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int FIRST_ELEMENT_ARRAY = 0;
    private static final int SECOND_ELEMENT_ARRAY = 1;
    private static final int THIRD_ELEMENT_ARRAY = 2;
    private static final int FOURTH_ELEMENT_ARRAY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder message = new StringBuilder("Report for period " 
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            message.append(System.lineSeparator()).append(name).append(" - ");
            int earned = 0;
            for (int i = 0; i < data.length; i++) {
                String[] dataLineArray = data[i].split(" ");
                LocalDate date = LocalDate.parse(dataLineArray[FIRST_ELEMENT_ARRAY], FORMATTER);
                if (date.isAfter(LocalDate.parse(dateFrom, FORMATTER))
                        && !date.isAfter(LocalDate.parse(dateTo, FORMATTER))
                        && name.equals(dataLineArray[SECOND_ELEMENT_ARRAY])) {
                    earned += Integer.parseInt(dataLineArray[THIRD_ELEMENT_ARRAY])
                            * Integer.parseInt(dataLineArray[FOURTH_ELEMENT_ARRAY]);
                    
                }
            }
            message.append(earned);
        }
        return message.toString();
    }
}
