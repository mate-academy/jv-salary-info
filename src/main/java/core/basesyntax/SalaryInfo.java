package core.basesyntax;
import java.time.LocalDate;
import exception.IllegalDateParametersException;

public class SalaryInfo  {
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
     * Создать пакет exception и в нём класс-ошибку IllegalDateParametersException. Сделать так,
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
            throws Exception {
        String[] partsOfDateFrom = dateFrom.split(".");
        String[] partsOfDateTo = dateTo.split(".");

        LocalDate homeDateFrom = LocalDate.of(Integer.parseInt(partsOfDateFrom[partsOfDateFrom.length - 1]), Integer.parseInt(partsOfDateFrom[partsOfDateFrom.length -2]), Integer.parseInt(partsOfDateFrom[partsOfDateFrom.length - 3]));
        LocalDate homeDateTo = LocalDate.of(Integer.parseInt(partsOfDateTo[partsOfDateTo.length -1]), Integer.parseInt(partsOfDateTo[partsOfDateTo.length -2]), Integer.parseInt(partsOfDateTo[partsOfDateTo.length - 3]));
        if(homeDateFrom.isAfter(homeDateTo)){
            throw new IllegalDateParametersException("Wrong parameters");
        }
        int salary  = 0;
        StringBuilder result = new StringBuilder();
        result.append("Отчет за период ").append(dateFrom).append(" - ").append(dateTo).append(" \n");
        for(int i = 0; i<names.length; i++){
            for(int j = 0; j < data.length; j++){
                String [] strAboutWorker = data[j].split(" ");
                String [] workerDate = strAboutWorker[0].split(" ");
                LocalDate localWorkerDate = LocalDate.of(Integer.parseInt(workerDate[2]), Integer.parseInt(workerDate[1]), Integer.parseInt(workerDate[0]));
                if(names[i].equalsIgnoreCase(strAboutWorker[1]) && localWorkerDate.isAfter(homeDateFrom) && localWorkerDate.isBefore(homeDateTo))
                    salary = salary + (Integer.parseInt(strAboutWorker[2]) * Integer.parseInt(strAboutWorker[3]));
            }
            result.append(names[i]).append(" - ").append(String.valueOf(salary)).append(" \n");
        }
        return result.toString();
    }
}
