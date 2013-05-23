package com.murdock.dubbo.offer;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.china.member.service.MemberReadService;
import com.alibaba.china.shared.manufacture.param.copyright.PostCopyrightOfferParam;
import com.alibaba.china.shared.manufacture.service.copyright.CopyrightOfferManageService;

/**
 * Hello world!
 */
public class App {

    static CopyrightOfferManageService copyrightOfferManageService;

    static MemberReadService           memberReadService;

    static {
        ApplicationContext app = new ClassPathXmlApplicationContext("NewOfferService.xml");
        copyrightOfferManageService = (CopyrightOfferManageService) app.getBean("copyrightOfferManageService");
        memberReadService = (MemberReadService) app.getBean("memberReadService");
    }

    /**
     * http://img.china.alibaba.com/img/ibank/2010/663/055/110550366_1146240514.jpg
     * 
     * @param args
     */
    public static void main(String[] args) {
        if (args != null && args.length >= 1) {
            if (args[0].equals("c")) {
                PostCopyrightOfferParam param = new PostCopyrightOfferParam();
                long userId = memberReadService.convertMemberIdToUserId("weipeng2k");
                param.setDescription("测试实施是是的发生的法拉盛大家发了静安寺打飞机阿斯顿发生地方阿加莎代发风笛");
                param.setDesignerUserId(userId);
                param.setPicUrlId("http://img.china.alibaba.com/img/ibank/2010/663/055/110550366_1146240514.jpg");
                param.setPrice(50D);
                param.setTitle("这是第三个测试");
                try {
                    Long id = copyrightOfferManageService.postCopyrightOffer(param);
                    System.out.println(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else if (args[0].equals("u")) {
                Long id = Long.parseLong(args[1]);
                try {
                    Random random = new Random();
                    double value = random.nextInt(1000) + 1;
                    copyrightOfferManageService.modifyCopyrightOfferPrice(id, value);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else if (args[0].equals("d")) {
                Long id = Long.parseLong(args[1]);
                try {
                    copyrightOfferManageService.deleteCopyrightOffer(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
