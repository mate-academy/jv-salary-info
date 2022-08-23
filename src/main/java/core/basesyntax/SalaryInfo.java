package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String account = new String();
        String compareName = new String();
        int[][][] calculation = new int[names.length + 2][2][3];
        int index = 0;
        /*
       calculation =
        { [0] [0] [0 - 2]- date from
          [0] [1] [0 - 2] - date to

          [1] [0] [0 - 2] - String data[i] 10.04.2019 John 7 100 = date only: 10 04 2019
          [1] [1] [0 - 1] - String data[i] 10.04.2019 John 7 100 = only: 7 100

          [2] [0] [0] - result of user -  names[0];
          [3] [0] [0] - result of user2 - names[1];
          [4] [0] [0] - result of user3 - names[2];
        */

        //send day "From" and "To" to int array
        calculation[0][0] = toIntArray(dateFrom, "\\.");
        calculation[0][1] = toIntArray(dateTo, "\\.");
        // Validation: input Data, bounds
        if (!validationData(calculation[0][0], calculation[0][1])) {
            return ("Data \"from\" can`t be bigger than data \"to\"");
        }
        for (int i = 0; i < data.length; i++) {
            // String - Array of day, month and year without current employee
            index = data[i].indexOf(' ');
            account = data[i].substring(0, index);
            calculation[1][0] = toIntArray(account, "\\.");
            // Data check
            if (!validationData(calculation[0][0], calculation[1][0])
                    || !validationData(calculation[1][0], calculation[0][1])) {
                continue;
            }
            // if data was applied, lets find person and write result
            account = data[i].substring(index + 1); //  "13.02.2019 John 7 100" = John 7 100;
            index = account.indexOf(' ');
            compareName = account.substring(0, index);
            account = account.substring(index + 1);
            calculation[1][1] = toIntArray(account, "\\ "); // calculation[1][1] = 7 100;
            for (int j = 0; j < names.length; j++) {
                if (!names[j].equals(compareName)) {
                    continue;
                }
                calculation[j + 2][0][0] += calculation[1][1][0] * calculation[1][1][1]; // result
            }
        }
        //Out information
        account = "";
        for (int i = 0; i < 3; i++) {
            account += System.lineSeparator() + names[i] + " - " + calculation[i + 2][0][0];
        }
        return "Report for period " + dateFrom + " - " + dateTo + account;
    }

    private boolean validationData(int [] smallerValues, int [] biggerValues) {
        for (int i = smallerValues.length - 1; i > -1; i--) {
            if (biggerValues[i] > smallerValues[i]) {
                return true;
            }
            if (biggerValues[i] < smallerValues[i]) {
                return false;
            }
        }
        return true;
    }

    private int [] toIntArray(String input, String divide) {
        String [] stringArray = input.split(divide);
        int [] outArray = new int [stringArray.length];

        for (int i = 0; i < stringArray.length; i++) {
            outArray[i] = Integer.parseInt(stringArray[i]);
        }
        return outArray;
    }
}
