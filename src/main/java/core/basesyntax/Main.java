package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        String[] salaryInfoSet = new String[]{
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100",
                "25.06.2019 John 11 50",
                "26.04.2019 Andrew 3 150",
                "13.02.2019 John 7 100",
                "26.04.2019 Kate 9 100"
        };

        final String[] secondScriptArray = {
                "13.07.2019 John 60 50",
                "15.07.2019 Andrew 3 200",
                "15.07.2019 Kate 10 100",

                "16.07.2019 Andrew 3 200",
                "16.07.2019 Kate 9 100",

                "10.08.2019 John 7 100",
                "08.08.2019 Kate 3 80",
                "11.08.2019 Andrew 8 100"
        };

        final String[] secondDates = {
                "14.07.2019",
                "10.08.2019",
        };

        final String[] dates = {
                "24.04.2019",
                "25.04.2019",
                "26.04.2019",
                "27.04.2019"
        };

        String[] namesArr = new String[]{
                "John",
                "Andrew",
                "Kate"
        };
        SalaryInfo salaryInfo = new SalaryInfo();
        String result = salaryInfo.getSalaryInfo(namesArr, secondScriptArray,
                secondDates[0], secondDates[1]);
        System.out.println(result);
    }
}

