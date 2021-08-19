package core.basesyntax;

import core.basesyntax.utils.ParserStringDateToLocalDate;
import java.time.LocalDate;

public class SalaryInfo {
    private final ParserStringDateToLocalDate parser = new ParserStringDateToLocalDate();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        int salaryFull = 0;
        for (String name : names) {
            for (int i = 0; i < data.length; i++) {
                String[] partsData = data[i].split(" ");

                LocalDate dateStart = parser.stringToLocalDate(dateFrom);
                LocalDate dateData = parser.stringToLocalDate(partsData[0]);
                LocalDate dateEnd = parser.stringToLocalDate(dateTo);

                if (name.equals(partsData[1])) {
                    if ((dateStart.isBefore(dateData)) || (dateStart.isEqual(dateData))) {
                        if ((dateEnd.isAfter(dateData)) || (dateEnd.isEqual(dateData))) {
                            if (partsData[1].equals(name)) {
                                int hours = Integer.parseInt(partsData[2]);
                                int salary = Integer.parseInt(partsData[3]);
                                salaryFull += hours * salary;
                            }
                        }
                    }
                }
            }
            builder.append(System.lineSeparator());
            builder.append(name);
            builder.append(" - ");
            builder.append(salaryFull);
            salaryFull = 0;
        }
        return "Report for period " + dateFrom + " - " + dateTo + builder;
    }
}
