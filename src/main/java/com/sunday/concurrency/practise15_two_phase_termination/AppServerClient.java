package com.sunday.concurrency.practise15_two_phase_termination;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by HenDiao on 2017/10/4.
 */
public class AppServerClient
{
    public static void main(String[] args) throws IOException, InterruptedException {
        AppServer server=new AppServer(1334);
        server.start();
        Thread.sleep(15_000L);

        server.shutdown();
    }
}
