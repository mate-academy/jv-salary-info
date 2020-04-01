package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;

import java.time.LocalDate;import java.time.format.DateTimeFormatter;


public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать пакет core.basesyntax.exception и в нём класс-ошибку IllegalDateParametersException. Сделать так,
     * чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        if(!(checkerDate(dateFrom, dateTo))) throw new IllegalDateParametersException();
        StringBuilder main = new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + "\n");
        int count;

        //System.out.println(Arrays.toString(data));

        for(String i: names) {
           main.append(i);
           count = 0;
           for(String j : data) {
               //System.out.println(i);
               //System.out.println(i.equals(j.split(" ")[1]));
               //System.out.println(checkerDate(j.split(" ")[0], dateTo));
               //System.out.println(dateTo);
               //System.out.println(checkerDate(dateFrom, j.split(" ")[0]));
               if(i.equals(j.split(" ")[1]) && checkerDate(j.split(" ")[0], dateTo) && checkerDate(dateFrom, j.split(" ")[0])) {
                   count += Integer.parseInt(j.split(" ")[2]) * Integer.parseInt(j.split(" ")[3]);
               }
           }
            main.append(" - ").append(count).append("\n");

        }


        return main.toString().substring(0,  main.length() - 1);
    }

    public Boolean checkerDate(String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return (dateFrom.equals(dateTo)) || LocalDate.parse(dateFrom, formatter).isBefore(LocalDate.parse(dateTo, formatter));

    }

}




