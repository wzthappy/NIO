package NIO.channel;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class DatagramChannelDemo {

  @Test  // 发送的实现
  public void sendDatagram() throws IOException, InterruptedException {
    // 打开 DatagramChannel
    DatagramChannel sendChannel = DatagramChannel.open();
    InetSocketAddress sengAddress = new InetSocketAddress("127.0.0.1", 9999);

    // 发送
    while (true) {
      ByteBuffer buffer = ByteBuffer.wrap("发送atguigu".getBytes(StandardCharsets.UTF_8));
      sendChannel.send(buffer, sengAddress);
      System.out.println("已经完成了发送");
      Thread.sleep(1000);
    }
  }

  @Test  // 接收的实现
  public void receiveDatagram() throws IOException {
    // 打开 DatagramChannel
    DatagramChannel receiveChannel = DatagramChannel.open();
    InetSocketAddress sengAddress = new InetSocketAddress(9999);

    // 绑定
    receiveChannel.bind(sengAddress);

    // buffer
    ByteBuffer buffer = ByteBuffer.allocate(1024);

    // 接收
    while (true) {
      buffer.clear();

      SocketAddress socketAddress = receiveChannel.receive(buffer);

      buffer.flip();

      System.out.println(socketAddress.toString());

      System.out.println(Charset.forName("UTF-8").decode(buffer));
    }
  }

  @Test // 连接 read 和 write
  public void testConnect() throws IOException {
    // 打开 DatagramChannel
    DatagramChannel connChannel = DatagramChannel.open();
    // 绑定
    connChannel.bind(new InetSocketAddress(9999));

    // 连接
    connChannel.connect(new InetSocketAddress("127.0.0.1", 9999));

    // write方法
    connChannel.write(ByteBuffer.wrap("发送atguigu".getBytes(StandardCharsets.UTF_8)));

    // buffer
    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    while (true) {
      readBuffer.clear();

      connChannel.read(readBuffer);

      readBuffer.flip();
      System.out.println(Charset.forName("UTF-8").decode(readBuffer));
    }
  }
}
