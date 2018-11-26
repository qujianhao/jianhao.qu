package com.wangtiansoft.KingDarts.config.captcha;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by weitong on 17/12/25.
 */
@org.springframework.stereotype.Component
public class CaptchaProvider {

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    // 验证码图片上显示的字符个数
    private static final int TEXT_SIZE = 4;
    //验证码图片上显示的字符的大小设置
    private static final int TEXT_WIDTH = 25;
    private static final int TEXT_HEIGHT = 35;
    // 验证码图片的大小设置
    private static final int IMAGE_CAPTCHA_WIDTH = 200;
    private static final int IMAGE_CAPTCHA_HEIGHT = 60;

    public boolean validate(String sessionId, String user_captcha) {
        String captcha = stringRedisTemplate.boundValueOps("CAPTCHA:" + sessionId).get();
        if (!StringUtils.equalsIgnoreCase(captcha, user_captcha)) {
            return false;
        }
        return true;
    }

    public BufferedImage createBufferedImage(HttpSession session) {
        BufferedImage bufferedImage = new BufferedImage(IMAGE_CAPTCHA_WIDTH, IMAGE_CAPTCHA_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        Color c = new Color(255, 255, 255);
        g.setColor(c);
        g.fillRect(0, 0, IMAGE_CAPTCHA_WIDTH, IMAGE_CAPTCHA_HEIGHT);
        char[] ch = "123456789abcdefghjkmnpqrstuvwxyz".toCharArray();
        Random r = new Random();
        int len = ch.length, index;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100)));
            g.setFont(new Font("Arial", 0, 30));
            g.drawString(ch[index] + "", (i * ((IMAGE_CAPTCHA_WIDTH - TEXT_SIZE * 10) / TEXT_SIZE)) + 20, RandomUtils.nextInt(TEXT_HEIGHT, IMAGE_CAPTCHA_HEIGHT - 10));
            sb.append(ch[index]);
        }
        stringRedisTemplate.boundValueOps("CAPTCHA:" + session.getId()).set(sb.toString(), 2, TimeUnit.MINUTES);
        return bufferedImage;
    }
}
