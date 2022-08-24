package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Converter implements TextGraphicsConverter {

    int MaxWidth;
    int MaxHeight;
    double maxRatio;
    TextColorSchema schema = new ColorConverter();

    public Converter(int MaxWidth, int MaxHeight) {
        this.MaxWidth = MaxWidth;
        this.MaxHeight = MaxHeight;
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));

        int curWidth = img.getWidth();    //ширина текущая
        int curHeight = img.getHeight();  //высота текущая

        int newWidth = curWidth;
        int newHeight = curHeight;

        double k = ((double) curWidth / (double) curHeight) * ((double) MaxHeight / (double) MaxWidth);


        if ((curWidth > curHeight) && (curWidth > MaxWidth)) {
            newWidth = MaxWidth;
            newHeight = (int) (MaxHeight / k);
        } else if ((curWidth < curHeight) && (curHeight > MaxHeight)) {
            newWidth = (int) (MaxWidth * k);
            newHeight = MaxHeight;
        } else if (k == 1.0) {
            newWidth = MaxWidth;
            newHeight = MaxHeight;
        }


        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        ImageIO.write(bwImg, "png", new File("out.png"));
        WritableRaster bwRaster = bwImg.getRaster();

        char[][] carArr = new char[newWidth][newHeight];

        for (int w = 0; w < newWidth; w++) {
            for (int h = 0; h < newHeight; h++) {
                int color = bwRaster.getPixel(w, h, new int[3])[0];
                char c = schema.convert(color);
                carArr[w][h] = c; //запоминаем символ c
            }
        }
        StringBuilder sb = new StringBuilder();
        String ss = "";
        for (int i = 0; i < newHeight; i++) {
            for (int j = 0; j < newWidth; j++) {
                sb.append(carArr[j][i]);
                sb.append(carArr[j][i]);
                sb.append(carArr[j][i]);
            }
            sb.append('\n');
        }
        ss = sb.toString();
        return ss;
    }

    @Override
    public void setMaxWidth(int width) {
        MaxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        MaxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
