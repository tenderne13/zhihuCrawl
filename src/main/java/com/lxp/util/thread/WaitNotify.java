package com.lxp.util.thread;

import com.lxp.vo.Installation;
import com.mysql.jdbc.TimeUtil;

import java.util.concurrent.TimeUnit;

public class WaitNotify implements Runnable{
    static boolean flag = true;
    static Installation installation = new Installation();
    private String location;

    public WaitNotify(String location) {
        this.location=location;
    }

    public void run() {

        synchronized (installation){
            System.out.println("wait线程获得对象锁");
            //installation.setLocation("北大地");
            System.out.println(location);
        }

    }


    public static void main (String[] ar) throws InterruptedException {
        Thread wait=new Thread(new WaitNotify("北大地"));
        wait.start();
        TimeUnit.SECONDS.sleep(2);
        Thread wait2=new Thread(new WaitNotify("首经贸"));
        wait2.start();
    }

}
