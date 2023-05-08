package cn.ac.big.bigd.webservice.send;

import cn.ac.big.bigd.webservice.code.SampleSynchronization;
import cn.ac.big.bigd.webservice.model.we.IacsUrlDataVo;
import cn.ac.big.bigd.webservice.utility.SendWeChatUtils;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * author chenx
 */
@RestController
public class SendWe {
    public static void sendSampleSynchronization(String userId,String content) {
        SendWeChatUtils msgUtils = new SendWeChatUtils();
        try {
            String token = msgUtils.getToken(SampleSynchronization.CORPID, SampleSynchronization.CORPSECRET);
            String postdata = msgUtils.createpostdata(userId, "text", SampleSynchronization.APPLICATION_ID, "content", content);
            String resp = msgUtils.post(SampleSynchronization.CHARSET, msgUtils.CONTENT_TYPE, (new IacsUrlDataVo()).getSendMessage_Url(), postdata, token);
//            String resp = msgUtils.post(Downtime.CHARSET, msgUtils.CONTENT_TYPE, (new IacsUrlDataVo()).getSendUserId_Url(), "{\"mobile\":\"15210356745\"}", token);
            System.out.println("获取到的token======>" + token);
            System.out.println("发送微信的响应数据======>" + resp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
