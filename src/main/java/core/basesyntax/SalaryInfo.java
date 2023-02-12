package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        int[] salaries = new int[names.length];
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        result.append(String.format("Report for period %s - %s", dateFrom, dateTo))
                .append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            for (String nameFromData : data) {
                try {
                    if (names[i].equals(nameFromData.split(" ")[1])
                            && dateFormat.parse(dateFrom).getTime()
                            <= dateFormat.parse(nameFromData.split(" ")[0]).getTime()
                            && dateFormat.parse(dateTo).getTime()
                            >= dateFormat.parse(nameFromData.split(" ")[0]).getTime()) {
                        salaries[i] += Integer.parseInt(nameFromData.split(" ")[2])
                                * Integer.parseInt(nameFromData.split(" ")[3]);

                    }
                } catch (ParseException e) {
                    System.out.println("Incorrect date");
                }
            }
            result.append(String.format("%s - %d", names[i], salaries[i]));
            if (i < names.length - 1) {
                result.append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
