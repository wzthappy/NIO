package NIO.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketChannelDemo {
  public static void main(String[] args) throws Exception {
    // 创建SocketChannel
    SocketChannel socketChannel =
        SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));

//    SocketChannel open1 = SocketChannel.open();
//    open1.connect(new InetSocketAddress("www.baidu.com", 80));

    // 设置堵塞和非堵塞
    socketChannel.configureBlocking(false);// 非堵塞
//    socketChannel.configureBlocking(true);// 非堵塞

    // 读操作
    ByteBuffer byteBuffer = ByteBuffer.allocate(16);
    socketChannel.read(byteBuffer);
    socketChannel.close();
    System.out.println((char) byteBuffer.get());
    System.out.println("read over");
  }
}
