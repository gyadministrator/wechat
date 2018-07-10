package com.gy.wechat.wechat.controller;

import com.gy.wechat.wechat.utils.Sha1Utils;
import com.gy.wechat.wechat.utils.WeChatUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author gaoyun
 * @version 1.0
 * @date 2018/7/10 16:58
 * @email 1984629668@qq.com
 * @description
 */
@RestController
@RequestMapping("/weChat")
public class WeChatController {

    /**
     * 微信URL接入验证
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping
    public String validate(String signature, String timestamp, String nonce, String echostr) {
        //1. 将token、timestamp、nonce三个参数进行字典序排序
        String[] arr = {timestamp, nonce, WeChatUtils.TOKEN};
        Arrays.sort(arr);
        //2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder sb = new StringBuilder();
        for (String temp : arr) {
            sb.append(temp);
        }
        //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (Sha1Utils.getSha1(sb.toString()).equals(signature)) {
            //接入成功
            return echostr;
        }
        //接入失败
        return null;
    }
}
