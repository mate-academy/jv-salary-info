package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        //created all variables
        int count = 0;
        int secondIndex;
        int thirdIndex;
        int fourthIndex;
        int fifthIndex;
        int sixthIndex;
        String nameFromData;
        String firstNumber;
        String secondNumber;
        int[] arr3 = new int[names.length];
        int day;
        int month;
        int year;
        int day2;
        int month2;
        int year2;
        int first;
        int second;
        int salary = 0;
        int day1;
        int month1;
        int day5;
        int month5;
        int day6;
        int month6;
        int[] arr2 = new int[names.length];

        /*Searching indexes in order to find numbers and names from 'dataFrom',
        // 'dataTo', 'names[]', 'data[]'
         text of data from variable 'dataFrom'
         */
        fourthIndex = dateFrom.indexOf(" ");
        fifthIndex = dateFrom.indexOf(" ", fourthIndex + 1);
        sixthIndex = dateFrom.indexOf(" ", fifthIndex + 1);
        String data1;
        data1 = dateFrom.substring(sixthIndex + 2, dateFrom.length() - 2);

        // text of data from variable 'dataTo'
        int seventhIndex;
        int eighthIndex;
        int ninthIndex;
        seventhIndex = dateFrom.indexOf(" ");
        eighthIndex = dateFrom.indexOf(" ", seventhIndex + 1);
        ninthIndex = dateFrom.indexOf(" ", eighthIndex + 1);
        String data2;
        data2 = dateTo.substring(ninthIndex,
                dateTo.length() - 1); // text of data from variable 'dataTo'

        // find indexes of '.' and ' ' in order to find names, day, year and month from array 'data'
        int tenthIndex;
        int eleventhIndex;
        int twelfthIndex;
        tenthIndex = data[0].indexOf(".");
        eleventhIndex = data[0].indexOf(".", tenthIndex + 1);
        twelfthIndex = data[0].indexOf(" ");

        /* here I check if 'day' is 03, I can not save this amount to int and I save only 3,
         if 12 or 23 I save this like that
         */
        int day3;
        day3 = Integer.parseInt(dateFrom.substring(sixthIndex + 2, sixthIndex + 3));
        if (day3 == 0) {
            day5 = Integer.parseInt(dateFrom.substring(sixthIndex + 3, sixthIndex + 4)); //dayFrom
        } else {
            day5 = Integer.parseInt(dateFrom.substring(sixthIndex + 2, sixthIndex + 4)); //dayFrom
        }

        //the same about month
        int month3;
        month3 = Integer.parseInt(dateFrom.substring(sixthIndex + 5, sixthIndex + 6));
        if (month3 == 0) {
            month5 = Integer.parseInt(dateFrom.substring(sixthIndex + 6,
                    sixthIndex + 7));//monthFrom
        } else {
            month5 = Integer.parseInt(dateFrom.substring(sixthIndex + 5,
                    sixthIndex + 7));//monthFrom
        }

        //here I find data to output
        int day4;
        int year3;
        year3 = Integer.parseInt(dateFrom.substring(sixthIndex + 8, sixthIndex + 12)); //yearFrom
        day4 = Integer.parseInt(dateTo.substring(ninthIndex, ninthIndex + 1));

        //the same about day from String 'dateTo'
        if (day4 == 0) {
            day6 = Integer.parseInt(dateTo.substring(ninthIndex + 1, ninthIndex + 2));//dayTo
        } else {
            day6 = Integer.parseInt(dateTo.substring(ninthIndex, ninthIndex + 2));//dayTo
        }

        //the same about month from String 'dateTo'
        int month4;
        month4 = Integer.parseInt(dateTo.substring(ninthIndex + 3, ninthIndex + 4));
        if (month4 == 0) {
            month6 = Integer.parseInt(dateTo.substring(ninthIndex + 4, ninthIndex + 5));//monthTo
        } else {
            month6 = Integer.parseInt(dateTo.substring(ninthIndex + 3, ninthIndex + 5)); //monthTo
        }
        //the same about year from String 'dateTo'
        int year4;
        StringBuilder result = new StringBuilder();
        year4 = Integer.parseInt(dateTo.substring(ninthIndex + 6, ninthIndex + 10)); //yearTo
        result.append("Report for period " + data1 + " - " + data2);

        //here we find day and month from array 'date'
        int firstIndex = 0;
        for (int i = 0; i < data.length; i++) {
            day1 = Integer.parseInt(data[i].substring(0, 1));
            month1 = Integer.parseInt(data[i].substring(tenthIndex + 1,
                    tenthIndex + 2));

            //here is the same logic as we find everything before, but we find this in array 'date'
            if (day1 == 0) {
                day = Integer.parseInt(data[i].substring(1, 2)); //day in data
            } else {
                day = Integer.parseInt(data[i].substring(0,
                        tenthIndex)); //day in data
            }
            if (month1 == 0) {
                month = Integer.parseInt(data[i].substring(tenthIndex + 1,
                        eleventhIndex)); //month in data
            } else {
                month = Integer.parseInt(data[i].substring(tenthIndex,
                        eleventhIndex)); //month in data
            }
            year = Integer.parseInt(data[i].substring(eleventhIndex + 1,
                    twelfthIndex)); // year in data

            //we check if data from array 'data' is in limits of 'dateFrom' and 'dateTo'
            if (day >= day5 && day <= day6 && month >= month5 && month <= month6
                    && (year == year3 || year == year4)) {

                //find indexes of two number in order to calculate salary
                firstIndex = data[i].indexOf(" ");
                secondIndex = data[i].indexOf(" ", firstIndex + 1);
                thirdIndex = data[i].indexOf(" ", secondIndex + 1);

                //find name from array 'data'
                nameFromData = data[i].substring(firstIndex + 1, secondIndex);
                firstNumber = data[i].substring(secondIndex + 1, thirdIndex);
                secondNumber = data[i].substring(thirdIndex + 1);

                first = Integer.parseInt(firstNumber); // first number in data
                second = Integer.parseInt(secondNumber); // second number in data
                salary = first * second; // salary per day in data

                /*we look if name from 'data' consist in array 'names'
                 and then add salary to array 'arr2'
                */
                for (int j = 0; j < names.length; j++) {
                    if (nameFromData.equals(names[j])) {
                        arr2[j] += salary;
                    }
                }
            }

            //each symbol from 'arr2' to 'arr3'
            for (int j = 0; j < names.length; j++) {
                arr3[j] = arr2[j];
            }
        }

        //output all results of our calculations
        for (int j = 0; j < names.length; j++) {
            result.append("\n").append(names[j]).append(" - ").append(arr3[j]);
        }
        return result.toString();
    }
}
