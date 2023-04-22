package NIO.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

// 通道之间数据传输
public class FileChannelDemo4 {

  // transferTo()
  public static void main(String[] args) throws IOException {
    // 创建两个FileChannel
    RandomAccessFile aFile = new RandomAccessFile("src/NIO/txt/demo1.txt", "rw");
    FileChannel fromChannel = aFile.getChannel();

    RandomAccessFile bFile = new RandomAccessFile("src/NIO/txt/demo3.txt", "rw");
    FileChannel toChannel = bFile.getChannel();

    // transferTo 传输到 toChannel
    long position = 0;
    long size = fromChannel.size();
    fromChannel.transferTo(position, size, toChannel);

    // 关闭channel
    toChannel.close();
    fromChannel.close();
    bFile.close();
    aFile.close();
  }
}
