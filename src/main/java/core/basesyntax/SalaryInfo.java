package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        
        final int indexOfHour = 2;
        final int indexOfSalary = 3;
        final int indexOfDate = 0;
        final int indexOfName = 1;
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        int summ = 0;
        
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        
        String[] masString;
        String[] dataAfter;
        
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilderreturn = new StringBuilder();
        stringBuilderreturn.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        
        for (int i = 0; i < data.length; i++) {
            masString = data[i].split(" ");
            LocalDate localDateMas = LocalDate.parse(masString[indexOfDate], formatter);
            
            if (localDateMas.isAfter(localDateFrom) && localDateMas.isBefore(localDateTo)
                    || (localDateMas.isEqual(localDateFrom)
                    || localDateMas.isEqual(localDateTo))) {
                stringBuilder.append(data[i]).append(";");
            }
        }
        
        if (stringBuilder.length() != 0) {
            dataAfter = stringBuilder.toString().split(";");
            
            for (int i = 0; i < names.length; i++) {
                for (int j = 0; j < dataAfter.length; j++) {
                    masString = dataAfter[j].split(" ");
                    if (names[i].equals(masString[indexOfName])) {
                        summ = summ + Integer.parseInt(masString[indexOfHour])
                                * Integer.parseInt(masString[indexOfSalary]);
                    }
                }
                stringBuilderreturn.append("\n").append(names[i]).append(" - ").append(summ);
                summ = 0;
            }
        } else {
            for (int i = 0; i < names.length; i++) {
                stringBuilderreturn.append("\n").append(names[i]).append(" - ").append(summ);
            }
        }
        return stringBuilderreturn.toString();
    }
}
