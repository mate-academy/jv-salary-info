package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_FORMAT = "d.MM.yyyy";
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        
        for (String name : names) {
            int salary = 0;
            
            for (String lineOfData : data) {
                String[] info = lineOfData.split(" ");
                LocalDate dateFromData = LocalDate.parse(info[0], formatter);
                String nameFromData = info[1];
                
                if (nameFromData.equals(name)
                        && (dateFromData.isAfter(localDateFrom)
                        || dateFromData.isEqual(localDateFrom))
                        && (dateFromData.isBefore(localDateTo)
                        || dateFromData.isEqual(localDateTo))) {
                    salary += Integer.parseInt(info[2])
                            * Integer.parseInt(info[3]);
                }
            }
            report.append("\n").append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
