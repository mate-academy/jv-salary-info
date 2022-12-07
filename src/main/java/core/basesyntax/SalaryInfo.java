package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class SalaryInfo {
    public String getSalaryInfo(String[] names,
                                String[] data,
                                String dateFrom,
                                String dateTo) {
        StringBuilder stb = new StringBuilder();
        String [] salaryForAllEmployees =
                getSalaryForAllEmployees(names, data, dateFrom, dateTo);
        for (int i = 0; i < salaryForAllEmployees.length; i++) {
            stb.append(System.lineSeparator()
                    + names[i]
                    + " "
                    + "-"
                    + " "
                    + getSalaryForAllEmployees(names, data, dateFrom, dateTo)[i]);
        }
        String intro = "Report for period " + dateFrom + " " + "-" + " " + dateTo;
        String result = intro + stb.toString();
        return result;
    }

    private boolean isValidForDate(String dateFrom, String dateTo, String dateOfEmployee) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendarFrom = Calendar.getInstance();
        Calendar calendarTo = Calendar.getInstance();
        Calendar calendarOfEmployee = Calendar.getInstance();
        try {
            calendarFrom.setTime(formatter.parse(dateFrom));
            calendarTo.setTime(formatter.parse(dateTo));
            calendarOfEmployee.setTime(formatter.parse(dateOfEmployee));

            calendarFrom.add(Calendar.DAY_OF_MONTH, -1);
            calendarTo.add(Calendar.DAY_OF_MONTH, 1);

            if (calendarOfEmployee.after(calendarFrom) && calendarOfEmployee.before(calendarTo)) {
                return true;
            }
            throw new ParseException("Should be date", 0);
        } catch (ParseException e) {
            return false;
        }
    }

    private String [] getSortedDateArray(String [] data, String dateFrom, String dateTo) {
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String dateOfEmployee = data[i].split(" ")[0];
            if (isValidForDate(dateFrom, dateTo, dateOfEmployee)) {
                stb.append(data[i] + ":");
            }
        }
        String [] str = stb.toString().split(":");
        return str;
    }

    private String getSalaryForOneEmployee(String name,
                                           String [] data,
                                           String dateFrom,
                                           String dateTo) {
        int workingHourse = 0;
        int salaryPerHour = 0;
        int salaryForEmplyee = 0;
        int countMatchesOfNames = 0;
        try {
            String [] sortedArray = getSortedDateArray(data, dateFrom, dateTo);
            if (Arrays.toString(sortedArray).equals("[]")) {
                return "0";
            }
            for (int i = 0; i < sortedArray.length; i++) {
                if (name.equals(sortedArray[i].split(" ")[1])) {
                    workingHourse = Integer.parseInt(sortedArray[i].split(" ")[2]);
                    salaryPerHour = Integer.parseInt(sortedArray[i].split(" ")[3]);
                    salaryForEmplyee += workingHourse * salaryPerHour;
                    countMatchesOfNames += 1;
                }
            }
            if (countMatchesOfNames > 0) {
                return Integer.toString(salaryForEmplyee);
            } else {
                throw new UserNotFoundException("User was not found.");
            }
        } catch (UserNotFoundException e) {
            return e.getMessage();
        }
    }

    private String [] getSalaryForAllEmployees(String[] names,
                                               String[] data,
                                               String dateFrom,
                                               String dateTo) {
        String [] infoForNames = new String[names.length];
        String [] sortedArray = getSortedDateArray(data, dateFrom, dateTo);
        for (int i = 0; i < names.length; i++) {
            infoForNames[i] = getSalaryForOneEmployee(names[i], data, dateFrom, dateTo);
        }
        return infoForNames;
    }
}
