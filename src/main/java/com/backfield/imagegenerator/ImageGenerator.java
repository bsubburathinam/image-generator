package com.backfield.imagegenerator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageGenerator {

    @Resource(name = "imageDrawFactory")
    ImageDrawFactory imageDrawFactory;

    public BufferedImage generateImageForString(String str) {
        ImageDraw imageDraw = imageDrawFactory.create(str.hashCode());
        return imageDraw.draw();
    }

    public static void main(String[] args) {
        if(args.length != 1) {
            throw new IllegalArgumentException("Expected email as argument");
        }
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");

        ImageGenerator imageGenerator = (ImageGenerator) applicationContext.getBean("imageGenerator");

        File file = new File("test.png");
        try {
            ImageIO.write(imageGenerator.generateImageForString(args[0]), "png", file);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

}
