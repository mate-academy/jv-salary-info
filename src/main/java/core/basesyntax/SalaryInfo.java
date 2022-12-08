package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SalaryInfo {
    private Date newDateTo;
    private int salary = 0;
    private String stringResult = "";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            stringResult = "Report for period " + dateFrom + " - " + dateTo;
            Date newDateFrom = getParseDate(dateFrom);
            newDateTo = getParseDate(dateTo);

            if (!dateFrom.equals(dateTo)) {
                addOneDayDateTo();
            }

            for (int i = 0; i <= names.length; i++) {
                if (i > 0) {
                    stringResult += "\n" + names[i - 1] + " - " + salary;
                    salary = 0;
                    if (i == names.length) {
                        salary = 0;
                        break;
                    }
                }
                for (int j = 0; j < data.length; j++) {
                    String[] tempArray = data[j].split(" ");
                    Date employeeDate = getParseDate(tempArray[0]);
                    if (names[i].equals(tempArray[1])) {
                        if (employeeDate.after(newDateFrom)
                                && employeeDate.before(newDateTo)) {
                            salary += Integer.parseInt(tempArray[2])
                                    * Integer.parseInt(tempArray[3]);
                        }
                    }
                }
            }
            return stringResult;
        } catch (ParseException exp) {
            throw new RuntimeException("Wrong date", exp);
        }
    }

    private static Date getParseDate(String dateFrom) throws ParseException {
        return new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
    }

    private void addOneDayDateTo() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newDateTo);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        newDateTo = calendar.getTime();
    }

    @Override
    public String toString() {
        return stringResult;
    }
}
