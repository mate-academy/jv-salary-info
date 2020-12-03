package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public static void main(String[] args) {

    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("d.MM.yyyy");
        StringBuilder returnValue = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo + "\n");
        int salary = 0;
        String[] listFromData;
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                listFromData = data[j].split("\\s+");
                if (names[i].equals(listFromData[1])
                        && (LocalDate.parse(listFromData[0], formatDate)
                        .isAfter(LocalDate.parse(dateFrom, formatDate))
                        || LocalDate.parse(listFromData[0], formatDate)
                        .compareTo(LocalDate.parse(dateFrom, formatDate)) == 0)
                        && (LocalDate.parse(listFromData[0], formatDate)
                        .isBefore(LocalDate.parse(dateTo, formatDate))
                        || LocalDate.parse(listFromData[0], formatDate)
                        .compareTo(LocalDate.parse(dateTo, formatDate)) == 0)) {
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
        return returnValue.toString().replace("[\\\r\\\n]+", "");
    }
}
