package com.eshop.utils;
 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.*;
//import com.sun.image.codec.jpeg.*;;
  
/** 
 * 图片压缩处理 
 * @author 崔素强 
 */  
public class Image {  
    private BufferedImage img;  
    private int width;  
    private int height;
    String filePath;
   /* @SuppressWarnings("deprecation")  */
   /* public static void main(String[] args) throws Exception {  
        System.out.println("开始：" + new Date().toLocaleString());  
        Image imgCom = new Image("C:/Users/uname/Pictures/Saved Pictures/200711912453162_2.jpg",);  
        imgCom.resize(300, 400);  
        System.out.println("结束：" + new Date().toLocaleString());  
    }*/  
    /** 
     * 构造函数 
     */  
    public Image(String fileName,String filePath) throws IOException {  
        File file = new File(fileName);// 读入文件  
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长 
        this.filePath = filePath;
    }  
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    public void resizeFix(int w, int h) throws IOException {  
        if (width / height > w / h) {  
            resizeByWidth(w);  
        } else {  
            resizeByHeight(h);  
        }  
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    public void resizeByWidth(int w) throws IOException {  
        int h = (int) (height * w / width);  
        resize(w, h);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    public void resizeByHeight(int h) throws IOException {  
        int w = (int) (width * h / height);  
        resize(w, h);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
    /*@SuppressWarnings("restriction")*/
	@SuppressWarnings("restriction")
	public void resize(int w, int h) throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        File destFile = new File(filePath);  
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG编码  
        out.close();  
    }  
}  
