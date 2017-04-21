package com.yitianyigexiangfa.netty.discardserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class DiscardServerHandle extends ChannelInboundHandlerAdapter {

	// ------------------------ 私有变量定义 -----------------------

	// ------------------------ 对象构造函数 -----------------------

	/**
	 * 对象构造函数
	 */
	public DiscardServerHandle() {
	}

	// ------------------------ 对象方法定义 -----------------------

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("从通道读到了信息");
		((ByteBuf) msg).release();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}
