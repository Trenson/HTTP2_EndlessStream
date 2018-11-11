# HTTP2_EndlessStream
HTTP2 used by netty, which can send endless stream from server, and can be reset from client.

Send a stream, for example, streamid=3, make endofstream=false, then send this stream per second, recever send rst stream when count=50.
