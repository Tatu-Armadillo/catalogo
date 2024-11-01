package br.com.fiap.catalogo.utils;

import java.time.LocalDateTime;

public class GenetedIdentifyKeyCode {
    
    public static String createCodeNumber() {
        return LocalDateTime.now().toString()
                .replace("'T'", "")
                .replace("-", "")
                .replace(":", "")
                .replace(".", "");
    }

}
