package com.yiming.learn.java.basic.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by yiming on 2017/10/18.
 */
public class ChannelDemo {


    public static void main(String[] args) {

        try {
            RandomAccessFile file = new RandomAccessFile("a.txt", "rw");
            FileChannel channel = file.getChannel();
            byteTest(channel);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static void byteTest(FileChannel channel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        int bytesRead = channel.read(byteBuffer);

        while (bytesRead != -1) {
            System.out.println("read " + bytesRead);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.println((char) byteBuffer.get());
            }
            byteBuffer.clear();
            bytesRead = channel.read(byteBuffer);
        }
    }

}
