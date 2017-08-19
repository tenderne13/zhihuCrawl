package com.lxp.util.weibo;

public class Lootery {
    public static void main(String[] args) {
        int a,b;
        //声明一个数组盛抽取的号码
        int[] select=new int[6];
        //声明一个红色奖箱数组
        int[] red= createBox(33);
        //声明一个蓝色奖箱数组
        int[] blue=createBox(16);
        //红色球抽奖
        for(int i=0;i<select.length;i++){
            int n=red.length;
            a=(int) (Math.random()*n);
            select[i]=red[a];
            red[a]=red[n-1];
            n--;
        }
        //蓝色球抽奖
        b=(int) (Math.random()*blue.length+1);

        System.out.print("双色球公布奖项:\n红色球部分:");
        for(int i=0;i<select.length;i++){
            if(i<(select.length-1)){
                System.out.print(select[i]+",");
            }else{
                System.out.print(select[i]+"\n");
            }
        }
        System.out.print("蓝色球部分:"+b);
    }


    public static int[] createBox(int size){
        int[] arr=new int[size];
        for(int i=0;i<arr.length;i++){
            arr[i]=i+1;
        }
        return arr;
    }
}
