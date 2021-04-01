package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        try {
            Date dateStart = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            Date dateEnd = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
            String[] splitData = new String[4];

            int[] salaries = new int[3];
            for (String info : data) {
                splitData = info.split(" ");
                Date newDate = new SimpleDateFormat("dd.MM.yyyy").parse(splitData[0]);
                if ((newDate.equals(dateEnd) || newDate.before(dateEnd))
                        && (newDate.after(dateStart) || newDate.equals(dateStart))) {
                    for (int i = 0; i < 3; i++) {
                        if (names[i].equals(splitData[1])) {
                            salaries[i] += Integer.parseInt(splitData[2])
                                    * Integer.parseInt(splitData[3]);
                            break;
                        }
                    }
                }
            }
            return "Report for period " + dateFrom + " - " + dateTo + "\nJohn - "
                    + salaries[0] + "\nAndrew - " + salaries[1] + "\nKate - " + salaries[2];

        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }
}
