
package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {

        Date dateStart = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
        Date dateEnd = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        int compare = dateEnd.compareTo(dateStart);
        if (compare == -1) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        StringBuilder result = new StringBuilder();
        for (String n : names) {
            int salary = 0;
            for (int i = 0;i < data.length; i++) {
                String[] str = data[i].split(" ");
                Date actual = new SimpleDateFormat("dd.MM.yyyy").parse(str[0]);
                if (str[1].equals(n) && dateEnd.compareTo(actual) >= 0
                        && dateStart.compareTo(actual) <= 0) {
                    salary += Integer.parseInt(str[2]) * Integer.parseInt(str[3]);
                }

            }
            result.append("\n").append(n).append(" - ").append(salary);
        }
        return ("Отчёт за период " + dateFrom + " - " + dateTo) + result.toString();
    }
}
