package com.jinrongtong.NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String args[]){
        RandomAccessFile aFile = null;
        try{
            //写文件
            aFile = new RandomAccessFile("nio.txt","rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            buf.put("hello".getBytes());
            buf.flip();
            while(buf.hasRemaining()){
                fileChannel.write(buf);
            }
            fileChannel.close();
            aFile.close();

            buf.clear();
            //读文件
            aFile = new RandomAccessFile("nio.txt","rw");
            fileChannel = aFile.getChannel();
            int bytesRead = fileChannel.read(buf);

            while(bytesRead != -1)
            {
                buf.flip();
                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                }

                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
