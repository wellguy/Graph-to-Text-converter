package ru.netology.graphics;

import ru.netology.graphics.image.Converter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter(200, 200); // Создайте тут объект вашего класса конвертера
        GServer server = new GServer(converter); // Создаём объект сервера

        server.start(); // Запускаем

        // Или то же, но с выводом на экран:
        //String url = "https://sun9-30.userapi.com/impf/c845123/v845123610/17cc98/xoJq8a6cS_Y.jpg?size=2068x2160&quality=96&sign=e4ba0dd48566858fb5811a30a38be77f&type=album";
        //String imgTxt = converter.convert(url);
        //System.out.println(imgTxt);
    }
}
