package com.lxp.util.weibo;

import java.io.*;

public class FileUtil {
    public static String path="C:/Users/Think/Desktop/tmp/";


    public static boolean writeToFile(String content,String fileName,String fileType){
        String fName="code";
        String fType="txt";
        if(!fileName.equals("")){
            fName=fileName;
        }
        if(!fileType.equals("")){
            fType=fileType;
        }
        try {

            File file = new File(path+fName+"."+fType);

            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();

            System.out.println("写入文件成功");
            return true;

        } catch (IOException e) {
            System.out.println("写入文件异常:"+e);
            return false;

        }

    }



    public static String readFileContent(String luckyNumber,String fileName) throws IOException {
        long start = System.currentTimeMillis();
        System.out.println("***********开始读取文件中的号码**************");
        String result=null;
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        String num=null;
        try {
            fileReader=new FileReader(path+fileName+".txt");
            bufferedReader=new BufferedReader(fileReader);

            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                result+=line+"\n";
                if(line.equals(luckyNumber)){
                    System.out.println("**********中奖了！！！中奖号码为:["+luckyNumber+"]***********");
                    num=luckyNumber;
                }else{
                    System.out.println("**********没中奖***********");
                }
            }

            long end = System.currentTimeMillis();
            if(num==null){
                System.out.println("最后抽奖结果为没中奖,用时："+(end-start)+"ms");
            }else {
                System.out.println("**********中奖了！！！中奖号码为:["+num+"],用时"+(end-start)+"ms***********");
            }

            return result;
        }catch (Exception e){
            System.out.println("异常:"+e);
            return null;
        }finally {
            if(bufferedReader!=null){
                bufferedReader.close();
            }
            if(fileReader!=null){
                fileReader.close();
            }
        }

    }
}
