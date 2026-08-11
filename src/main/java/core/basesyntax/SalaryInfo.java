package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int FORMATTER_LENGTH = 10;
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        LocalDate startDate = getLocalDate(dateFrom);
        LocalDate endDate = getLocalDate(dateTo);
        
        builder.append(dateFrom).append(" - ")
                .append(dateTo);
        
        for (String name : names) {
            int totalSalary = 0;
            
            for (String record : data) {
                if (record.contains(name)) {
                    LocalDate recordDate = getLocalDate(record.substring(0, FORMATTER_LENGTH));
                    
                    if ((recordDate.isAfter(startDate) && recordDate.isBefore(endDate)
                            || (recordDate.isEqual(startDate) || recordDate.isEqual(endDate)))) {
                        int salaryPerPeriod = getSalaryPerPeriod(record);
                        
                        totalSalary += salaryPerPeriod;
                    }
                }
                
            }
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        
        return builder.toString();
    }
    
    private static LocalDate getLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
    
    private static int getSalaryPerPeriod(String record) {
        int lastSpaceIndex = record.lastIndexOf(" ");
        int secondLastSpaceIndex = record.lastIndexOf(" ", lastSpaceIndex - 1);
        int workingHours = Integer.parseInt(
                record.substring(secondLastSpaceIndex, lastSpaceIndex)
                .trim()
        );
        int incomePerHour = Integer.parseInt(record.substring(lastSpaceIndex).trim());
        
        return incomePerHour * workingHours;
    }
}
