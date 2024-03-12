package org.application.executor;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Main main = new Main();
        ExecutorService executorService = main.generateExecutor(10);

        List<String> urlPaths = List.of("pass1", "pass2", "pass3", "pass4");

        Queue<Future<String>> futures = new LinkedBlockingQueue<>();

        for (String urlPath : urlPaths) {
            Future<String> future = executorService.submit(() -> makeRequest(urlPath));
            futures.add(future);
        }

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
        executorService.shutdown();
    }

    private static String makeRequest(String urlPath) {
        System.out.println("request to: " + urlPath);
        return urlPath.concat(" - response 200");
    }

    public ExecutorService generateExecutor(int threads) {
        return Executors.newFixedThreadPool(threads);
    }
}
