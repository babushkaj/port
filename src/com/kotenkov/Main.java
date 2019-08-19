package com.kotenkov;

//  Задание 4. Многопоточность. Порт . Корабли заходят в порт для
//  разгрузки/загрузки контейнеров. Число контейнеров, находящихся в текущий момент
//  в порту и на корабле, должно быть неотрицательным и не превышающим заданную
//  грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
//  У одного причала может стоять один корабль. Корабль может загружаться у причала
//  или разгружаться.

public class Main {

    public static void main(String[] args) {
        Port port = new Port(300, 500, 3);

        Thread t1 = new Thread(new Ship("Ship1", 0, 70, true, port));
        Thread t2 = new Thread(new Ship("Ship2", 0, 80, true, port));
        Thread t3 = new Thread(new Ship("Ship3", 0, 60, true, port));
        Thread t4 = new Thread(new Ship("Ship4", 90, 90, false, port));
        Thread t5 = new Thread(new Ship("Ship5", 90, 90, false, port));
        Thread t6 = new Thread(new Ship("Ship6", 0, 30, true, port));
        Thread t7 = new Thread(new Ship("Ship7", 40, 40, false, port));
        Thread t8 = new Thread(new Ship("Ship8", 0, 50, true, port));
        Thread t9 = new Thread(new Ship("Ship9", 70, 70, false, port));
        Thread t10 = new Thread(new Ship("Ship10", 60, 60, false, port));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        //тормозим main-поток, чтобы увидеть количество контейнеров в порту после обслуживания всех кораблей

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nTotal in the port: " + port.getCurrentLoad());
    }

}
