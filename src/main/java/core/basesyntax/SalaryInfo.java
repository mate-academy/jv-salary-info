package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        if (names == null || data == null || dateFrom == null
                || dateTo == null) {
            throw new DataGettingException("One or more input arguments are null");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate newDateFrom;
        LocalDate newDateTo;

        try {
            newDateFrom = LocalDate.parse(dateFrom, formatter);
            newDateTo = LocalDate.parse(dateTo, formatter);
        } catch (Exception e) {
            throw new DataGettingException("Invalid date format. Excepted format: dd.MM.yyyy");
        }

        if (newDateFrom.getYear() > newDateTo.getYear()
                || (newDateFrom.getYear() == newDateTo.getYear()
                && newDateFrom.getMonthValue() > newDateTo.getMonthValue())
                || (newDateFrom.getYear() == newDateTo.getYear()
                && newDateFrom.getMonthValue() == newDateTo.getMonthValue()
                && newDateFrom.getDayOfMonth() > newDateTo.getDayOfMonth())) {
            throw new DataGettingException("Start date cannot be later than end date");
        }

        if (newDateFrom.equals(newDateTo)) {
            return returnInfo(ifEqual(names), dateFrom, dateTo);
        }

        String[] startResult = new String[data.length];

        for (int i = 0; i < data.length; i++) {
            String[] dataArrayI = data[i].split(" ");

            LocalDate date;

            try {
                date = LocalDate.parse(dataArrayI[0], formatter);
            } catch (Exception e) {
                throw new DataGettingException("Invalid date in data entry: " + data[i]);
            }

            boolean withinDateRange = date.isAfter(newDateFrom) && date.isBefore(newDateTo)
                    || date.isEqual(newDateFrom) && date.isBefore(newDateTo)
                    || date.isAfter(newDateFrom) && date.isEqual(newDateTo);

            if (withinDateRange) {
                String name = dataArrayI[1];
                int hours = Integer.parseInt(dataArrayI[2]);
                int money = Integer.parseInt(dataArrayI[3]);
                int salary = hours * money;

                addPersonToStartResult(startResult, name, salary);
            }
        }

        return returnInfo(makeInOrder(startResult, names), dateFrom, dateTo);
    }

    public String returnInfo(String[] array, String dataF, String dataT) {
        StringBuilder str = new StringBuilder();
        str.append("Report for period ").append(dataF).append(" - ").append(dataT)
                .append(System.lineSeparator());

        for (int i = 0; i < array.length; i++) {
            str.append(array[i]);
            if (i < array.length - 1) {
                str.append(System.lineSeparator());
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

    public String[] makeInOrder(String[] arrayToOrder, String[] names) {
        String[] orderedArray = new String[names.length];
        String[] beforeSorting = new String[names.length];
        int num = 0;

        for (int g = 0; g < arrayToOrder.length; g++) {
            if (arrayToOrder[g] != null) {
                beforeSorting[num] = arrayToOrder[g];
                num++;
            }
        }

        beforeSorting = Arrays.copyOf(beforeSorting, num);

        for (int i = 0; i < names.length; i++) {
            String currentName = names[i];

            for (int j = 0; j < beforeSorting.length; j++) {
                String[] afterSplit = beforeSorting[j].split(" - ");
                String searchName = afterSplit[0];

                if (currentName.equals(searchName)) {
                    orderedArray[i] = beforeSorting[j];
                    break;
                }
            }
        }

        return orderedArray;
    }

    public String[] ifEqual(String[] names) {
        String[] resultArray = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            resultArray[i] = names[i] + " - 0";
        }

        return resultArray;
    }
}
