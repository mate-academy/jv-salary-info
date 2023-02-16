package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator() + name + " - ");
            int salary = 0;
            for (String line : data) {
                String date = line.substring(0, 10);
                if (line.matches("(.*)" + name + "(.*)")) {
                    try {
                        if (DATE_FORMAT.parse(date).after(DATE_FORMAT.parse(dateFrom))
                                && DATE_FORMAT.parse(date).before(DATE_FORMAT.parse(dateTo))
                                || DATE_FORMAT.parse(date).equals(DATE_FORMAT.parse(dateTo))) {
                            String[] hoursAndPerHour = line.split(name + " ")[1].split(" ");
                            int hours = Integer.parseInt(hoursAndPerHour[0]);
                            int perHour = Integer.parseInt(hoursAndPerHour[1]);
                            salary += hours * perHour;
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException("Cannot parse the date from data");
                    }
                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }
}
