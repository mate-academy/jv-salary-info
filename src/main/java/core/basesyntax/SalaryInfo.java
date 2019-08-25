package core.basesyntax;

public class SalaryInfo {

    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * <p>
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * <p>
     * names:
     * Сергей
     * Андрей
     * София
     * <p>
     * data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * <p>
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int dateFromInt = Integer.parseInt(dateFrom.substring(0, 2));
        int dateToInt = Integer.parseInt(dateTo.substring(0, 2));
        int period = dateToInt - dateFromInt;
        String result = " ";
        String s = data[0].substring(0, 2);
        System.out.println(s);

        for (int i = 0; i < names.length; i++) {
            if (dateFromInt == Integer.parseInt(data[i].substring(0, 2))) {
                for (int j = 0; j < period; j++) {
                    String[] updatedData = new String[period];
                    updatedData = data[j].split(" ");
                    int income = Integer.parseInt(updatedData[2]) * Integer.parseInt(updatedData[3]);

                    result += names[i].substring(12, 17);
                    System.out.println(data[j].substring(0, 2));
                }
            } else {
                result += names[i] + " - " + "0";
            }
        }


        String statement = "Отчёт за период " + dateFrom + " - " + dateTo;
        return result;
    }
}


//str += "";
//updatedData = updatedData[2] + "\n";


//  "25.04.2019 Сергей 60 50"
                    /*
                    String str = "geekss@for@geekss";
        String[] arrOfStr = str.split("@", 2);

        for (String a : arrOfStr)
            System.out.println(a);
                     */


/*                //System.out.println("dateTo: " + dateTo.split("."));
        //period += 0;

names[0] = "Sergei";
        names[1] = "Andrew";
        names[3] = "Sophie";
        data = {"date", "name", "hours", "rate"};*/

//  str.split(" ");

        /*
           1. По дате вычисляем п
         */


/*        System.out.println("Отчёт за период ");
        int salary = 0; //  to be removed
        StringBuilder sb = new StringBuilder(salary);
        for (int i = 0; i < names.length; i++) {  // try to transform into for each statement
            System.out.println(names[i] + " - " + salary);
            sb.append(names[i] + " - " + salary);
        }
        return sb.toString();*/
