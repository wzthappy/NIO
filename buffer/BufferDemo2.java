package NIO.buffer;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo2 {

  private static final int start = 0;
  private static final int size = 1024;

  @Test // 内存映射文件io
  public void b04() throws IOException {
    RandomAccessFile raf = new RandomAccessFile("src/NIO/txt/demo1.txt", "rw");
    FileChannel fc = raf.getChannel();
    MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, start, size);

    mbb.put(0, (byte) 97);
    mbb.put(1023, (byte) 122);
    raf.close();
  }

  @Test // 直接缓冲区
  public void b03() throws IOException {
    String infile = "src/NIO/txt/demo1.txt";
    FileInputStream fin = new FileInputStream(infile);
    FileChannel finChannel = fin.getChannel();

    String outfile = "src/NIO/txt/demo4.txt";
    FileOutputStream fout = new FileOutputStream(outfile);
    FileChannel foutChannel = fout.getChannel();

    // 创建直接缓存区
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    while (true) {
      buffer.clear();
      int r = finChannel.read(buffer);
      if (r == -1) {
        break;
      }
      buffer.flip();
      foutChannel.write(buffer);
    }
  }

  @Test // 只读缓冲区
  public void b02() {
    ByteBuffer buffer = ByteBuffer.allocate(10);

    for (int i = 0; i < buffer.capacity(); i++) {
      buffer.put((byte) i);
    }

    // 创建只读缓冲区
    ByteBuffer readOnly = buffer.asReadOnlyBuffer();

    readOnly.flip();
    while (readOnly.remaining() > 0) {
      System.out.println(readOnly.get());
    }

    for (int i = 0; i < buffer.capacity(); i++) {
      byte b = buffer.get(i);
      b *= 10;
      buffer.put(i, b);
    }

    System.out.println("---------");

    readOnly.position(0);
    readOnly.limit(buffer.capacity());
    while (readOnly.remaining() > 0) {
      System.out.println(readOnly.get());
    }
  }

  @Test // 缓冲区分片
  public void b01() {
    ByteBuffer buffer = ByteBuffer.allocate(10);

    for (int i = 0; i < buffer.capacity(); i++) {
      buffer.put((byte) i);
    }

    // 创建子缓冲区
    buffer.position(3);
    buffer.limit(7);

    ByteBuffer slice = buffer.slice();

    // 改变子缓冲区内容
    for (int i = 0; i < slice.capacity(); i++) {
      byte b = slice.get(i);
      b *= 10;
      slice.put(i, b);
    }

    buffer.position(0);
    buffer.limit(buffer.capacity());

    while (buffer.remaining() > 0) {
      System.out.println(buffer.get());
    }
  }
}
