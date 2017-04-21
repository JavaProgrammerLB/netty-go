package com.yitianyigexiangfa.netty.telnet;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;

@Sharable
public class TelnetServerHandler extends SimpleChannelInboundHandler<String> {

	// ------------------------ 私有变量定义 -----------------------

	// ------------------------ 对象构造函数 -----------------------

	/**
	 * 对象构造函数
	 */
	public TelnetServerHandler() {
	}

	// ------------------------ 对象方法定义 -----------------------

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
		ctx.write("It is " + new Date() + " now.\r\n");
		ctx.flush();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String request) throws Exception {
		boolean close = false;
		String response = null;
		if (request.isEmpty()) {
			response = "Please type something";
		} else if ("bye".equals(request.toLowerCase())) {
			response = "Hava a good day, bye";
			close = true;
		} else {
			response = "Did you said: " + request + "\r\n";
		}
		ChannelFuture future = ctx.write(response);
		if (close) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
