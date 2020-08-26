package se128.jupiter.zuulserver.util.requestutils;


import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import se128.jupiter.zuulserver.util.msgutils.Msg;
import se128.jupiter.zuulserver.util.msgutils.MsgCode;
import se128.jupiter.zuulserver.util.msgutils.MsgUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class RequestUtil {
    public void sendJsonBack(HttpServletResponse response){
        Msg msg = MsgUtil.makeMsg(MsgCode.NOT_AVAILABLE);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()){
            writer.print(JSONObject.fromObject(msg));
        }catch (IOException e){
            System.out.println("send json back error");
        }

        System.out.println("OUTTTTTTTTTTTTTT");
    }
}
