package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATA_DATE_INDEX = 0;
    private static final int DATA_NAME_INDEX = 1;
    private static final int DATA_WORKING_HOURS_INDEX = 2;
    private static final int DATA_PER_HOUR_SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
           
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;

            for (String record : data) {
                String[] dataLineArray = record.split(" ");               
                LocalDate dataBaseDate = LocalDate.parse(dataLineArray[DATA_DATE_INDEX], FORMATTER);

                if (dataBaseDate.compareTo(localDateFrom) >= 0 
                        && dataBaseDate.compareTo(localDateTo) <= 0 
                        && dataLineArray[DATA_NAME_INDEX].equals(name)) {
                    salary += Integer.parseInt(dataLineArray[DATA_PER_HOUR_SALARY_INDEX]) 
                            * Integer.parseInt(dataLineArray[DATA_WORKING_HOURS_INDEX]);
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }         
        return builder.toString();
    }
}
