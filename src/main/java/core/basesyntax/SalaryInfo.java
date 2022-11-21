package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom;
        LocalDate localDateTo;
        StringBuilder builder = new StringBuilder();

        try {
            localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
            localDateTo = LocalDate.parse(dateTo, FORMATTER);
        } catch (DateTimeParseException e) {          
            throw new RuntimeException("Input date has incorrect format");
        }
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        try {
            for (String name : names) {
                int salary = 0;
    
                for (String record : data) {
                    String[] dataLineArray = record.split(" ");               
                    LocalDate dataBaseDate = LocalDate.parse(dataLineArray[0], FORMATTER);
    
                    if (dataBaseDate.compareTo(localDateFrom) >= 0 
                            && dataBaseDate.compareTo(localDateTo) <= 0 
                            && dataLineArray[1].equals(name)) {
                        salary += Integer.parseInt(dataLineArray[2]) 
                                * Integer.parseInt(dataLineArray[3]);
                    }
                }
                builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            }         
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new RuntimeException("Data has incorrect format", e);
        }
        return builder.toString();
    }
}
