package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append(System.lineSeparator());
        int count = 0;
        for (String name : names) {
            int salary = 0;
            count++;
            for (String item : data) {
                String[] processData = item.split(" ");
                if (processData[1].equals(name)) {
                    if (!(result.toString().contains(name))) {
                        result.append(name).append(" - ");
                    }
                    try {
                        Date dayFrom = format.parse(dateFrom);
                        Date dayTo = format.parse(dateTo);
                        Date empDays = format.parse(processData[0]);
                        if (empDays.compareTo(dayFrom) >= 0 && (dayTo.compareTo(empDays) >= 0)) {
                            salary += (Integer.parseInt(processData[2])
                                    * Integer.parseInt(processData[3]));
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException("Wrong date format - "
                                + "correct date format is: dd.MM.yyyy", e);
                    }
                }
            }
            if (count < names.length) {
                result.append(salary).append(System.lineSeparator());
            } else {
                result.append(salary);
            }
        }
        return result.toString();
    }
}
