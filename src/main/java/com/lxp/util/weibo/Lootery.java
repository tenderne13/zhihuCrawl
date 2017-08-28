package com.lxp.util.weibo;

import java.io.IOException;

public class Lootery {
    public static void main(String[] args) throws IOException {
        StringBuffer buffer = new StringBuffer();
        long start = System.currentTimeMillis();
        for(int i=0;i<3000;i++){
            buffer.append(getLooteryNumber()+"\n");
        }
        long end=System.currentTimeMillis();
        System.out.println("抽奖完毕,用时:"+(end-start)+"ms");
        //FileUtil.writeToFile(buffer.toString(),"lootery","txt");
        String lottery = FileUtil.readFileContent("24,16,15,21,33,25,11", "lootery");
    }


    public static int[] createBox(int size){
        int[] arr=new int[size];
        for(int i=0;i<arr.length;i++){
            arr[i]=i+1;
        }
        return arr;
    }


    public static String getLooteryNumber(){
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

        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<select.length;i++){
            /*if(i<(select.length-1)){
                buffer.append(select[i]+",");
            }else{
                buffer.append(select[i]);
            }*/
            buffer.append(select[i]+",");
        }
        buffer.append(b);
        return buffer.toString();
    }
}
