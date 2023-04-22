package NIO.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// FileChannel写操作
public class FileChannelDemo2 {
  public static void main(String[] args) throws IOException {
    // 创建FileChannel
    RandomAccessFile aFile = new RandomAccessFile("src/NIO/txt/demo1.txt", "rw");
    FileChannel channel = aFile.getChannel();

    // 创建buffer对象
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    String newData = "Data atguigu 中国";
    buffer.clear(); // 清空buffer中的数据
    // 写入内容
    buffer.put(newData.getBytes());

    buffer.flip(); // 模式转换

    // FileChannel完成最终实现
    while (buffer.hasRemaining()) {
      channel.write(buffer);
    }

    // 关闭channel
    channel.close();
    aFile.close();
  }
}
