package com.kotenkov;

//  Задание 4. Многопоточность. Порт . Корабли заходят в порт для
//  разгрузки/загрузки контейнеров. Число контейнеров, находящихся в текущий момент
//  в порту и на корабле, должно быть неотрицательным и не превышающим заданную
//  грузоподъемность судна и вместимость порта. В порту работает несколько причалов.
//  У одного причала может стоять один корабль. Корабль может загружаться у причала
//  или разгружаться.

public class Ship implements Runnable{
    private String name;
    private int currentLoad;
    private int maxLoad;
    private boolean needLoad;
    private Port port;

    public Ship(String name, int currentLoad, int maxLoad, boolean needLoad, Port port) {
        this.name = name;
        this.currentLoad = currentLoad;
        this.maxLoad = maxLoad;
        this.needLoad = needLoad;
        this.port = port;
    }

    @Override
    public void run() {
        while(true){
            if(port.isFreePier(this)){
                port.processShip(this);
                break;
            }
        }
    }

    public int giveCargo(int cargo){
        if(currentLoad >= cargo){
            currentLoad -= cargo;
            System.out.println(name + ": cargo is given (" + cargo + ")");
            return cargo;
        }else{
            System.out.println(name + " can't give the cargo!");
            return  0;
        }
    }

    public void takeCargo(int cargo){
        if(currentLoad + cargo <= maxLoad){
            currentLoad += cargo;
            System.out.println(name + ": cargo is taken (" + cargo + ")");
        }else{
            System.out.println(name + "can't take the cargo!");
        }
    }

    public String getName() {
        return name;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public int getMaxLoad() {
        return maxLoad;
    }

    public boolean isNeedLoad() {
        return needLoad;
    }
}
