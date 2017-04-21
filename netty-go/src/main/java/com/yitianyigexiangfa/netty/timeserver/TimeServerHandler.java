package com.yitianyigexiangfa.netty.timeserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

	// ------------------------ 私有变量定义 -----------------------

	// ------------------------ 对象构造函数 -----------------------

	/**
	 * 对象构造函数
	 */
	public TimeServerHandler() {
	}

	// ------------------------ 对象方法定义 -----------------------

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		ByteBuf time = ctx.alloc().buffer(4);
		time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
		ChannelFuture f = ctx.writeAndFlush(time);
		f.addListener(new ChannelFutureListener() {

			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				assert f == future;
				ctx.close();
			}
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
