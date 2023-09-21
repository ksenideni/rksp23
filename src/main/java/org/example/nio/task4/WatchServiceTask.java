package org.example.nio.task4;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceTask {
    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("src/main/resources/nio");
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                String kindName = event.kind().name();
                switch (kindName) {
                    case "ENTRY_CREATE" -> System.out.println("Создался новый файл:" + event.context());
                    case "ENTRY_MODIFY" -> System.out.println("Произошли изменения в файле:" + event.context());
                    case "ENTRY_DELETE" -> System.out.println("Файл удален:" + event.context());
                }
            }


            key.reset();
        }
    }
}
