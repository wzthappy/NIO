package NIO.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

// 通道之间数据传输
public class FileChannelDemo3 {

  // transferFrom()
  public static void main(String[] args) throws IOException {
    // 创建两个FileChannel
    RandomAccessFile aFile = new RandomAccessFile("src/NIO/txt/demo1.txt", "rw");
    FileChannel fromChannel = aFile.getChannel();

    RandomAccessFile bFile = new RandomAccessFile("src/NIO/txt/demo2.txt", "rw");
    FileChannel toChannel = bFile.getChannel();

    // fromChannel 传输到 toChannel
    long position = 0;
    long size = fromChannel.size();
    toChannel.transferFrom(fromChannel, position, size);


    // 关闭channel
    toChannel.close();
    fromChannel.close();
    bFile.close();
    aFile.close();
  }
}
