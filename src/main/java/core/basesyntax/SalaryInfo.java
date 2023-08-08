package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        try {
            Date localDateFrom = FORMATTER.parse(dateFrom);
            Date localDateTo = FORMATTER.parse(dateTo);
            for (int i = 0; i < names.length; i++) {
                int tempSalaryForPerson = 0;
                for (int j = 0; j < data.length; j++) {
                    if (data[j].contains(names[i])) {
                        String[] dataForOnePerson = data[j].split(" ");
                        Date personDay = FORMATTER.parse(dataForOnePerson[0]);
                        if (personDay.compareTo(localDateFrom) >= 0
                                && personDay.compareTo(localDateTo) <= 0) {
                            tempSalaryForPerson += Integer.parseInt(dataForOnePerson[2])
                                    * Integer.parseInt(dataForOnePerson[3]);
                        }
                    }
                }
                builder.append(System.lineSeparator()).append(names[i]).append(" - ")
                        .append(tempSalaryForPerson);
            }
        } catch (ParseException e) {
            throw new RuntimeException("Can`t parse date", e);
        }
        return builder.toString();
    }
}
