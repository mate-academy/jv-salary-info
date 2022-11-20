package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] totalSalaries = new int[names.length];
        LocalDate localDateFrom;
        LocalDate localDateTo;
        String result = "Report for period " + dateFrom + " - " + dateTo;

        try {
            localDateFrom = LocalDate.parse(dateFrom, formatter);
            localDateTo = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {          
            return "Input date has incorrect format\n" + e;
        }
      
        for (int i = 0; i < data.length; i++) {
            try {
                String[] dataLineArray = data[i].split(" ");
                LocalDate dataBaseDate = LocalDate.parse(dataLineArray[0], formatter);

                if (dataBaseDate.compareTo(localDateFrom) >= 0 
                        && dataBaseDate.compareTo(localDateTo) <= 0) {
                    int index = getNameIndex(names, dataLineArray[1]);                    
                    totalSalaries[index] += Integer.parseInt(dataLineArray[2]) 
                            * Integer.parseInt(dataLineArray[3]);
                }
            } catch (NameDoesNotExist | RuntimeException e) {
                return "Data has incorrect format\n" + e;
            }            
        }             
        for (int i = 0; i < totalSalaries.length; i++) {
            result += System.lineSeparator() + names[i] + " - " + totalSalaries[i];
        }
        return result;
    }

    private int getNameIndex(String[] names, String name) throws NameDoesNotExist {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return i;
            }          
        } 
        throw new NameDoesNotExist("Name was not found in the data array");     
    }
}
