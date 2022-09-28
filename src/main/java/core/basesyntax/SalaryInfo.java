package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int INDEX_OF_HOUR = 2;
    static final int INDEX_OF_SALARY = 3;
    static final int INDEX_OF_DATE = 0;
    static final int INDEX_OF_NAME = 1;
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int summ = 0;
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        String[] masString;
        StringBuilder stringBuilderreturn = new StringBuilder();
        stringBuilderreturn.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        
        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < data.length; i++) {
                masString = data[i].split(" ");
                LocalDate localDateMas = LocalDate.parse(masString[INDEX_OF_DATE], formatter);
                if (localDateMas.isAfter(localDateFrom) && localDateMas.isBefore(localDateTo)
                        || (localDateMas.isEqual(localDateFrom)
                        || localDateMas.isEqual(localDateTo))) {
                    if (names[j].equals(masString[INDEX_OF_NAME])) {
                        summ = summ + Integer.parseInt(masString[INDEX_OF_HOUR])
                                * Integer.parseInt(masString[INDEX_OF_SALARY]);
                    }
                }
            }
            stringBuilderreturn.append("\n").append(names[j]).append(" - ").append(summ);
            summ = 0;
        }
        return stringBuilderreturn.toString();
    }
}

