package NIO.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelDemo {
  public static void main(String[] args) throws IOException, InterruptedException {
    // 端口号
    int port = 8888;

    // buffer
    ByteBuffer buffer = ByteBuffer.wrap("hello atguigu 中国".getBytes());

    // ServerSocketChannel
    ServerSocketChannel ssc = ServerSocketChannel.open();

    // 绑定
    ssc.socket().bind(new InetSocketAddress(port));

    // 设置非阻塞模式
    ssc.configureBlocking(false);

    // 监听是否有新链接传入
    while (true) {
      System.out.println("Waiting for connections");
      SocketChannel sc = ssc.accept();
      if (sc == null) { // 没有链接传入
        System.out.println("null");
        Thread.sleep(2000);
      } else {
        System.out.println("Incoming connection from: " + sc.socket().getRemoteSocketAddress());
        buffer.rewind(); // 指针0
        sc.write(buffer);
        sc.close();
      }
    }
  }
}
