package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator() + name + " - ");
            int salary = 0;
            for (String line : data) {
                String date = line.substring(0, 10);
                if (line.matches("(.*)" + name + "(.*)")) {
                    try {
                        if (format.parse(date).after(format.parse(dateFrom))
                                && format.parse(date).before(format.parse(dateTo))
                                || format.parse(date).equals(format.parse(dateTo))) {
                            String[] hoursAndPerHour = line.split(name + " ")[1].split(" ");
                            int hours = Integer.parseInt(hoursAndPerHour[0]);
                            int perHour = Integer.parseInt(hoursAndPerHour[1]);
                            salary += hours * perHour;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }
}
