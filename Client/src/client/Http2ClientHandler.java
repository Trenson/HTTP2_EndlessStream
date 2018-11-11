package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http2.*;

public class Http2ClientHandler extends DelegatingDecompressorFrameListener {
    private Http2Request mHttp2Request;
    int cnt = 0;

    public Http2ClientHandler(Http2Connection connection, Http2FrameListener listener) {
        super(connection, listener);
        mHttp2Request = new HelloWorldHttp2HandlerBuilder().build();
    }

    @Override
    public int onDataRead(ChannelHandlerContext ctx, int streamId, ByteBuf data, int padding, boolean endOfStream) throws Http2Exception {
        System.out.println("onData: " + data + ",cnt = " + cnt);
        cnt++;
        if (cnt == 50 && mHttp2Request != null) {
            mHttp2Request.rstStream(ctx, streamId);
        }
        return super.onDataRead(ctx, streamId, data, padding, endOfStream);
    }

    @Override
    public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int padding, boolean endStream) throws Http2Exception {
        super.onHeadersRead(ctx, streamId, headers, padding, endStream);
    }

    @Override
    public void onHeadersRead(ChannelHandlerContext ctx, int streamId, Http2Headers headers, int streamDependency, short weight, boolean exclusive, int padding, boolean endStream) throws Http2Exception {
        super.onHeadersRead(ctx, streamId, headers, streamDependency, weight, exclusive, padding, endStream);
    }

    @Override
    protected EmbeddedChannel newContentDecompressor(ChannelHandlerContext ctx, CharSequence contentEncoding) throws Http2Exception {
        return super.newContentDecompressor(ctx, contentEncoding);
    }

    @Override
    protected CharSequence getTargetContentEncoding(CharSequence contentEncoding) throws Http2Exception {
        return super.getTargetContentEncoding(contentEncoding);
    }
}
