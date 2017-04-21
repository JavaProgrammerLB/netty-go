package com.yitianyigexiangfa.netty.timeserver;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient {

	// ------------------------ 私有变量定义 -----------------------

	// ------------------------ 对象构造函数 -----------------------

	/**
	 * 对象构造函数
	 */
	public TimeClient() {
	}

	// ------------------------ 对象方法定义 -----------------------

	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = 8001;

		NioEventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new TimeClientHandler());
				}
			});

			ChannelFuture f = b.connect(host, port).sync(); // (5)

			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}

	}
}
