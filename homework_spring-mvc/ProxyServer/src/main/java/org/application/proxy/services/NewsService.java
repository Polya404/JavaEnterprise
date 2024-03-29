package org.application.proxy.services;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NewsService {
    private static final String key = "c55628071c6d456f950973a77c328542";
    NewsApiClient newsApiClient = new NewsApiClient(key);

    @SneakyThrows
    public List<String> getTopHeaders() {
        CompletableFuture<List<String>> future = new CompletableFuture<>();
        final List<String> headers = new ArrayList<>();
        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .q("Ukraine")
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {
                        for (Article article : response.getArticles()) {
                            headers.add(article.getTitle());
                        }
                        future.complete(addStringToEachArticle(headers));
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                        future.completeExceptionally(throwable);
                    }
                }
        );
        log.info("Sending headers");
        return future.get();
    }

    private List<String> addStringToEachArticle(List<String> headers) {
        return headers.stream()
                .map(header -> header.concat(" Glory to Ukraine"))
                .collect(Collectors.toList());
    }
}
