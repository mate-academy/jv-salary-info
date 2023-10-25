package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
    
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    
        for (String name : names) {
            int totalSalary = 0;
    
            for (String entry : data) {
                String[] parts = entry.split(" ");
                String entryDateStr = parts[0];
                String employeeName = parts[1];
                int hoursWorked = Integer.parseInt(parts[2]);
                int hourlyRate = Integer.parseInt(parts[3]);
    
                try {
                    Date entryDate = dateFormat.parse(entryDateStr);
                    Date fromDate = dateFormat.parse(dateFrom);
                    Date toDate = dateFormat.parse(dateTo);
    
                    if (employeeName.equals(name) && entryDate.compareTo(fromDate) 
                            >= 0 && entryDate.compareTo(toDate) <= 0) {
                        totalSalary += hoursWorked * hourlyRate;
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Can't process data", e);
                }
            }
            
            result.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
    
        return result.toString();
    }
}
