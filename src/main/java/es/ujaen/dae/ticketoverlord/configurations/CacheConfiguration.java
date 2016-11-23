package es.ujaen.dae.ticketoverlord.configurations;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheConfiguration {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        caches.add(new ConcurrentMapCache("venuesCache"));
        caches.add(new ConcurrentMapCache("ticketsCache"));
        caches.add(new ConcurrentMapCache("usersCache"));
        cacheManager.setCaches(caches);
        return cacheManager;
    }
}
