package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    static final String CONSTANT_FORMAT = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws DateTimeParseException {
        String result = "";
        try {
            StringBuilder builder = new StringBuilder();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(CONSTANT_FORMAT);
            LocalDate startDate = LocalDate.parse(dateFrom, formatter).minusDays(1);
            LocalDate finishDate = LocalDate.parse(dateTo, formatter).plusDays(1);
            for (String name : names) {
                int salaryTotal = 0;
                for (String datum : data) {
                    int days = Integer.parseInt(datum.split(" ")[2]);
                    int price = Integer.parseInt(datum.split(" ")[3]);
                    String nameTemp = datum.split(" ")[1];
                    LocalDate dateTemp = LocalDate.parse(datum.split(" ")[0], formatter);
                    if (dateTemp.isAfter(startDate) && dateTemp.isBefore(finishDate)
                            && name.equals(nameTemp)) {
                        salaryTotal += days * price;
                    }
                }
                builder.append(System.lineSeparator());
                builder.append(name);
                builder.append(" - ");
                builder.append(salaryTotal);
            }
            result = "Report for period " + dateFrom + " - " + dateTo + builder;
        } catch (DateTimeParseException exc) {
            System.out.println("Date Parameters are incorrect.");
        }
        return result;
    }
}
