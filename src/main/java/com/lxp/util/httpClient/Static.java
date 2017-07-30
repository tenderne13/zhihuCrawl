package com.lxp.util.httpClient;

import java.util.Random;

public class Static {

    public static  String[] cookies=new String[4];

    static{
        cookies[0]="Mi4wQUJBTXVSOEtnZ2dBa0lMX29NQWtEQmNBQUFCaEFsVk5GVi1sV1FDYkhqSDhSb1NKTFpPblRBVlZuWEZBd3lhMGRR|1501418005|f73707d9599c73e667b37490d807ef65b2c4d66a";
        cookies[1]="Mi4wQUNEQ3pIM3pKQXdBa0lMX29NQWtEQmNBQUFCaEFsVk53MS1sV1FESTExeENIZzVNeDFUS0pnU3hmSTNfYi1XTU9n|1501418179|70e3a052115376442f8daabb964e23ceb745eb11";
        cookies[2]="Mi4wQUFDQzZjcnpKQXdBa0lMX29NQWtEQmNBQUFCaEFsVk5FbUNsV1FCU1lka1Z0UHNxc1lLYXplZ05HcVFVY0gzZG5R|1501418258|68d8da1a73e7a65bc68ec32000c894bc2ce81142";
        cookies[3]="Mi4wQUpCQ2Z2enpKQXdBa0lMX29NQWtEQmNBQUFCaEFsVk5SR0NsV1FBX0swU0dPVEhoRG4tVzQyVHhMeWRjNk1uOTNn|1501418308|b576bb2e5872702111c7a690cb7a6d1236c740f1";
    }


    public  static  String getCookie(){
        Random random=new Random();
        int num =random.nextInt(4);
        return  cookies[num];
    }

    public static void main(String[] args){
        System.out.println(getCookie());

    }
}
