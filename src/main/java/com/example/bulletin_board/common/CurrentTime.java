package com.example.bulletin_board.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTime {
    public static String getCurrentTime(){
        LocalDateTime now = LocalDateTime.now();
        return now.toString();
    }
}
