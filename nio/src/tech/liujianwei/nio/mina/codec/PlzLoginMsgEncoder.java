package tech.liujianwei.nio.mina.codec;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import tech.liujianwei.nio.mina.model.AbstractPlzMsg;
import tech.liujianwei.nio.mina.model.LoginMsg;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PlzLoginMsgEncoder implements MessageEncoder {

    private static final Set<Class> TYPES;

    static {
        Set<Class> types = new HashSet();
        types.add(LoginMsg.class);
        TYPES = Collections.unmodifiableSet(types);
    }

    @Override
    public Set<Class> getMessageTypes() {
        return TYPES;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput output) throws Exception {
        if (message != null) {
            AbstractPlzMsg msg = (AbstractPlzMsg) message;
            ByteBuffer buf = ByteBuffer.allocate(msg.getLength());
            buf.putString(msg.getData(), Charset.forName("UTF-8").newEncoder());
            buf.flip();
            output.write(buf);
        }
    }
}
