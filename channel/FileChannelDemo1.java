package NIO.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo1 {
  // FileChannel读取数据到buffer中
  public static void main(String[] args) throws IOException {
    // 创建FileChannel
    RandomAccessFile aFile = new RandomAccessFile("src/NIO/txt/demo1.txt", "rw"); // rw读写
    FileChannel channel = aFile.getChannel();

    // 创建Buffer
    ByteBuffer buffer = ByteBuffer.allocate(1024);

    // 读取数据到buffer中
    int bytesRead = channel.read(buffer);
    while (bytesRead != -1) {
      System.out.println("读取了: " + bytesRead);
      buffer.flip(); // 读写转换
      while (buffer.hasRemaining()) {
        System.out.println((char) buffer.get());
      }
      buffer.clear();
      bytesRead = channel.read(buffer);
    }

    channel.close();
    aFile.close();
    System.out.println("结束了");
  }
}
