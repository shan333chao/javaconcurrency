package com.sunday.concurrency.practise11_balking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by HenDiao on 2017/10/3.
 */
public class BalkingData {
    private final String fileName;
    private static volatile boolean changed;
    private String content;

    public BalkingData(String fileName,String content) {
        this.fileName = fileName;
        this.changed = false;
        this.content = content;
    }

    public synchronized void change(String newContent) {
        this.content = newContent;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }
        doSave();

        this.changed = false;

    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls do save, content="+content);
        try (Writer writer = new FileWriter(fileName, true)) {

            writer.write(content);
            writer.write("\n");
            writer.flush();
        }



    }
}
