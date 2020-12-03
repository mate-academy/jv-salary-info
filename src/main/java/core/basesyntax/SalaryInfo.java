package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("d.MM.yyyy");
        StringBuilder returnValue = new StringBuilder();
        returnValue = returnValue.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append("\n");
        int salary = 0;
        String[] listFromData;
        LocalDate dateWhenWorking;
        LocalDate dateFromWorking = LocalDate.parse(dateFrom, formatDate);
        LocalDate dateToWorking = LocalDate.parse(dateTo, formatDate);
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                listFromData = data[j].split("\\s+");
                dateWhenWorking = LocalDate.parse(listFromData[0], formatDate);
                if (names[i].equals(listFromData[1])
                        && (dateWhenWorking.isAfter(dateFromWorking)
                        || dateWhenWorking.compareTo(dateFromWorking) == 0)
                        && (dateWhenWorking.isBefore(dateToWorking)
                        || dateWhenWorking.compareTo(dateToWorking) == 0)) {
                    salary += Integer.parseInt(listFromData[2])
                            * Integer.parseInt(listFromData[3]);
                }
            }
            if (i == names.length - 1) {
                returnValue.append(names[i] + " - " + salary);
            } else {
                returnValue.append(names[i] + " - " + salary + "\n");
            }
            salary = 0;
        }
        return returnValue.toString();
    }
}
