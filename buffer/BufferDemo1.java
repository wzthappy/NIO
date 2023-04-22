package NIO.buffer;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class BufferDemo1 {

  @Test
  public void buffer01() throws IOException {
    // FileChannel
    RandomAccessFile aFile = new RandomAccessFile("src/NIO/txt/demo1.txt", "rw");
    FileChannel channel = aFile.getChannel();

    // 创建buffer, 大小
    ByteBuffer buffer = ByteBuffer.allocate(1024);

    // 读
    int byteRead = channel.read(buffer);
    while (byteRead != -1) {
      // read模式
      buffer.flip();

      while (buffer.hasRemaining()) {
        System.out.println(buffer.get());
      }
      buffer.clear();
      byteRead = channel.read(buffer);
    }
    aFile.close();
  }

  @Test
  public void buffer02() {
    // 创建buffer
    IntBuffer buffer = IntBuffer.allocate(8);

    // buffer放数据
    for (int i = 0; i < buffer.capacity(); i++) {
      int j = 2 * (i + 1);
      buffer.put(j);
    }

    // 切换模式
    buffer.flip();

    // 获取值
    while (buffer.hasRemaining()) {
      System.out.println(buffer.get());
    }
  }
}
