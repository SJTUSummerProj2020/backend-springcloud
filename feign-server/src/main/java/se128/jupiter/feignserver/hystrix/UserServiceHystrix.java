package se128.jupiter.feignserver.hystrix;

import org.springframework.stereotype.Component;
import se128.jupiter.feignserver.service.UserService;
import util.msgutils.Msg;
import util.msgutils.MsgCode;
import util.msgutils.MsgUtil;

@Component
public class UserServiceHystrix implements UserService {

    @Override
    public Msg getAllUser() {
        return MsgUtil.makeMsg(MsgCode.DATA_ERROR);
        //return "error";
    }
}
