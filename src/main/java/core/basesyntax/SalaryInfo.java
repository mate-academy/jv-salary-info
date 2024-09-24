package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salaryFirst = 0;
        int salarySecond = 0;
        int salaryThird = 0;
        String firstName = "";
        String secondName = "";
        String thirdName = "";

        String [] splitDataFrom = dateFrom.split("[.]");
        String [] splitDataTo = dateTo.split("[.]");
        String [] splitCurrentData = new String[3];
        String [] splitInputString = new String[4];

        int currentDay = 0;
        int currentMonth = 0;
        int toDay = Integer.parseInt(splitDataTo[0]);
        int fromDay = Integer.parseInt(splitDataFrom[0]);
        int toMonth = Integer.parseInt(splitDataTo[1]);
        int fromMonth = Integer.parseInt(splitDataFrom[1]);
        firstName = names[0];

        for (int i = 1; i < names.length; i++) {
            if (!firstName.equals(names[i])) {
                secondName = names[i];
            }
            break;
        }
        for (int i = 1; i < names.length; i++) {
            if (!firstName.equals(names[i]) && !secondName.equals(names[i])) {
                thirdName = names[i];
                break;
            }
        }

        //sort by data
        for (int i = 0; i < data.length; i++) {
            splitInputString = data[i].split(" ");
            splitCurrentData = splitInputString[0].split("[.]");
            currentDay = Integer.parseInt(splitCurrentData[0]);
            currentMonth = Integer.parseInt(splitCurrentData[1]);

            if ((fromDay <= currentDay && currentDay <= toDay
                    && currentMonth == toMonth && currentMonth == fromMonth)
                    || (fromDay <= currentDay && fromMonth == currentMonth
                    && currentMonth < toMonth)
                    || (fromMonth < currentMonth && currentDay <= toDay
                    && currentMonth == toMonth)) {
                if (splitInputString[1].equals(firstName)) {
                    salaryFirst += Integer.parseInt(splitInputString[2])
                            * Integer.parseInt(splitInputString[3]);
                }
                if (splitInputString[1].equals(secondName)) {
                    salarySecond += Integer.parseInt(splitInputString[2])
                            * Integer.parseInt(splitInputString[3]);
                }
                if (splitInputString[1].equals(thirdName)) {
                    salaryThird += Integer.parseInt(splitInputString[2])
                            * Integer.parseInt(splitInputString[3]);
                }
            }
        }
        return "Report for period " + dateFrom + " - " + dateTo
                + "\r\n" + firstName + " - " + salaryFirst
                + "\r\n" + secondName + " - " + salarySecond
                + "\r\n" + thirdName + " - " + salaryThird;
    }
}
