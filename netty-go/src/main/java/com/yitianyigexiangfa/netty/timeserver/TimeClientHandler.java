package com.yitianyigexiangfa.netty.timeserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

	// ------------------------ 私有变量定义 -----------------------

	// ------------------------ 对象构造函数 -----------------------

	/**
	 * 对象构造函数
	 */
	public TimeClientHandler() {
	}

	// ------------------------ 对象方法定义 -----------------------

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf m = (ByteBuf) msg;
		long currentTime = (m.readUnsignedInt() - 2208988800L) * 1000L;
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(currentTime)));
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
