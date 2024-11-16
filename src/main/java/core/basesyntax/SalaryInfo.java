package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws DataGettingException {

        if (names == null || data == null || dateFrom == null
                || dateTo == null) {
            throw new DataGettingException("one or more input arguments are null");
        }

        int dayFrom = 0;
        int monthFrom = 0;
        int yearFrom = 0;

        int dayTo = 0;
        int monthTo = 0;
        int yearTo = 0;

        try {
            dayFrom = getNumberOfDay(dateFrom, "day");
            monthFrom = getNumberOfDay(dateFrom, "month");
            yearFrom = getNumberOfDay(dateFrom, "year");

            dayTo = getNumberOfDay(dateTo, "day");
            monthTo = getNumberOfDay(dateTo, "month");
            yearTo = getNumberOfDay(dateTo, "year");
        } catch (WrongWordException e) {
            System.out.println("Input wrong word");
        }

        if (yearFrom > yearTo || (yearFrom == yearTo && monthFrom > monthTo)
                || (yearFrom == yearTo && monthFrom == monthTo && dayFrom > dayTo)) {
            throw new DataGettingException("Start date cannot be later than end date");
        }

        String[] startResult = new String[data.length];

        for (int i = 0; i < data.length; i++) {
            String[] dataArrayI = data[i].split(" ");
            String[] dataPeriod = dataArrayI[0].split("\\.");

            int day = Integer.parseInt(dataPeriod[0]);
            int month = Integer.parseInt(dataPeriod[1]);
            int year = Integer.parseInt(dataPeriod[2]);

            boolean withinDateRange = (year == yearFrom && (month >= monthFrom
                    && day >= dayFrom) && (month <= monthTo && day <= dayTo))
                    || (year == yearTo && (month <= monthTo && day <= dayTo)
                    && (month >= monthFrom && day >= dayFrom))
                    || (year > yearFrom && year < yearTo);

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
