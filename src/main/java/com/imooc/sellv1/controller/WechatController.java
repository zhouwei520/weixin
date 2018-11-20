package com.imooc.sellv1.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

/**
 * 描述:
 * 微信使用SDK
 *
 * @author mac
 * @create 2018-11-14 7:54 PM
 */
@Slf4j
@Controller
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl){

        //1.0 配置
        //调用方法
        String url="http://zwsell.natapp1.cc/sell/wechat/userInfo";
        String redirectUrl=  wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_USER_INFO,URLEncoder.encode(returnUrl));
        return "redirect:" +redirectUrl;
    }

    @GetMapping("/userInfo")
    public String  userInfo(
            @RequestParam("code") String code,
            @RequestParam("state") String redirectUrl){

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new  WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken= wxMpService.oauth2getAccessToken(code);
        }
        catch (WxErrorException ex){
            ex.printStackTrace();
        }
        String openid=wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" +redirectUrl +"?openid="+openid;
    }

}