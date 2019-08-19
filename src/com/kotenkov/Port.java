package com.kotenkov;

//  Задание 4. Многопоточность. Порт . Корабли заходят в порт для
//  разгрузки/загрузки контейнеров. Число контейнеров, находящихся в текущий момент
//  в порту и на корабле, должно быть неотрицательным и не превышающим заданную
//  грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
//  У одного причала может стоять один корабль. Корабль может загружаться у причала
//  или разгружаться.

public class Port {
    private volatile int currentLoad;
    private volatile int maxLoad;
    private int pierQuantity;

    public Port(int currentLoad, int maxLoad, int pierQuantity) {
        this.currentLoad = currentLoad;
        this.maxLoad = maxLoad;
        this.pierQuantity = pierQuantity;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public synchronized void giveCargo(Ship ship){
        int cargo = ship.getMaxLoad() - ship.getCurrentLoad();

        if(currentLoad >= cargo){
            currentLoad -= cargo;
            ship.takeCargo(cargo);
            System.out.println("Port: " + cargo + " is given to " + ship.getName());
            pierQuantity++;
            System.out.println(ship.getName() + " out from the port. ====>>");
        }else{
            System.out.println("!!!Port can't give the cargo to " + ship.getName());
            pierQuantity++;
            System.out.println(ship.getName() + " out from the port. ====>>");
        }

    }

    public synchronized void takeCargo(Ship ship){
        int cargo = ship.giveCargo(ship.getCurrentLoad());

        if(currentLoad + cargo <= maxLoad){
            currentLoad += cargo;
            System.out.println("Port: " + cargo + " is taken from " + ship.getName());
            pierQuantity++;
            System.out.println(ship.getName() + " out from the port. ====>>");
        }else{
            System.out.println("!!!Port can't take the cargo from " + ship.getName());
            pierQuantity++;
            System.out.println(ship.getName() + " out from the port. ====>>");
        }

    }

    public synchronized boolean isFreePier(Ship ship){
        if(pierQuantity > 0){
            pierQuantity--;
            System.out.println(ship.getName() + " is in the port. <<====");
            return true;
        }
        return false;
    }

    public void processShip(Ship ship){
                try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(ship.isNeedLoad()){
            giveCargo(ship);
        } else {
            takeCargo(ship);
        }
    }

}
