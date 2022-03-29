package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        try {
            Date firstDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            Date secondDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
            for (String name: names) {
                builder.append("\n");
                int salary = 0;
                for (int i = 0; i < data.length; i++) {
                    String[] dataFormat = data[i].split(" ");
                    Date tempDate = new SimpleDateFormat("dd.MM.yyyy").parse(dataFormat[0]);
                    if (name.equals(dataFormat[1])
                            && (firstDate.equals(tempDate) || secondDate.equals(tempDate))) {
                        salary += Integer.parseInt(dataFormat[2]) * Integer.parseInt(dataFormat[3]);
                    }
                    if (name.equals(dataFormat[1])
                            && tempDate.after(firstDate) && tempDate.before(secondDate)) {
                        salary += Integer.parseInt(dataFormat[2]) * Integer.parseInt(dataFormat[3]);
                    }
                }
                builder.append(name).append(" - ").append(salary);
            }
        } catch (ParseException e) {
            System.out.println("Something may be wrong!");
            e.printStackTrace();
        }
        return "Report for period " + dateFrom + " - " + dateTo
                + builder.toString();
    }
}
