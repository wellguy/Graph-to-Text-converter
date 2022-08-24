package ru.netology.graphics.image;

import ru.netology.graphics.image.TextColorSchema;

public class ColorConverter implements TextColorSchema {


    @Override
    public char convert(int color) {
        if (color >= 0 && color < 30) {
            return '@';
        } else if (color >= 32 && color < 62) {
            return '%';
        } else if (color >= 62 && color < 92) {
            return '$';
        } else if (color >= 92 && color < 122) {
            return '#';
        } else if (color >= 122 && color < 152) {
            return '=';
        } else if (color >= 152 && color < 182) {
            return '/';
        } else if (color >= 182 && color < 212) {
            return '!';
        } else if (color >= 212 && color < 255) {
            return '.';
        }
        return '?';
    }

    ;
}
