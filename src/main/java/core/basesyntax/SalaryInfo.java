package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws DataGettingException {

        if (names == null || data == null || dateFrom == null
                || dateTo == null) {
            throw new DataGettingException("One or more input arguments are null");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate newDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate newDateTo = LocalDate.parse(dateTo, formatter);

        if (newDateFrom.getYear() > newDateTo.getYear()
                || (newDateFrom.getYear() == newDateTo.getYear()
                && newDateFrom.getMonthValue() > newDateTo.getMonthValue())
                || (newDateFrom.getYear() == newDateTo.getYear()
                && newDateFrom.getMonthValue() == newDateTo.getMonthValue()
                && newDateFrom.getDayOfMonth() > newDateTo.getDayOfMonth())) {
            throw new DataGettingException("Start date cannot be later than end date");
        }

        String[] startResult = new String[data.length];

        for (int i = 0; i < data.length; i++) {
            String[] dataArrayI = data[i].split(" ");

            LocalDate date = LocalDate.parse(dataArrayI[0], formatter);

            boolean withinDateRange = date.isAfter(newDateFrom) && date.isBefore(newDateTo);

            if (withinDateRange) {
                String name = dataArrayI[1];
                int hours = Integer.parseInt(dataArrayI[2]);
                int money = Integer.parseInt(dataArrayI[3]);
                int salary = hours * money;

                addPersonToStartResult(startResult, name, salary);
            }
        }

        return returnInfo(startResult, dateFrom, dateTo);
    }

    public int getNumberOfDay(String date, String period) throws WrongWordException {
        char[] array = date.toCharArray();
        if (period.toLowerCase().equals("day")) {
            if (array[0] == '0') {
                return Character.getNumericValue(array[1]);
            } else {
                String day = "" + array[0] + array[1];
                return Integer.parseInt(day);
            }
        } else if (period.toLowerCase().equals("month")) {
            if (array[3] == '0') {
                return Character.getNumericValue(array[4]);
            } else {
                String month = "" + array[3] + array[4];
                return Integer.parseInt(month);
            }
        } else if (period.toLowerCase().equals("year")) {
            String year = "" + array[6] + array[7] + array[8] + array[9];
            return Integer.parseInt(year);
        } else {
            throw new WrongWordException("Input wrong word");
        }

    }

    public String returnInfo(String[] array, String dataF, String dataT) {
        StringBuilder str = new StringBuilder("Report for period ");
        str.append(dataF).append(" - ").append(dataT);
        for (String entry : array) {
            if (entry != null) {
                str.append("\n").append(entry);
            }
        }
        return str.toString();
    }

    public void addPersonToStartResult(String[] array, String name, int salary) {
        boolean personExist = false;

        if (array[0] != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    String[] parts = array[i].split(" - ");
                    if (parts[0].equals(name)) {
                        int currentSalary = Integer.parseInt(parts[1]);
                        currentSalary += salary;
                        parts[1] = String.valueOf(currentSalary);
                        array[i] = String.join(" - ", parts);
                        personExist = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        if (!personExist) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == null) {
                    array[i] = name + " - " + salary;
                    break;
                }
            }
        }
    }

}
