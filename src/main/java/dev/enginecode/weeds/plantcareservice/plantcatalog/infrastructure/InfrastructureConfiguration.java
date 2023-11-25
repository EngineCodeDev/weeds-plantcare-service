package dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.concurrent.TimeUnit;

@Configuration
@EntityScan("dev.enginecode.eccommons.infrastructure.json.model")
@EnableJpaRepositories("dev.enginecode.eccommons.infrastructure.json.repository")
@EnableCaching
public class InfrastructureConfiguration {
    @Value("${cache.caffeine.expireAfterWriteDurationInMinutes}")
    private long expireAfterWriteDurationInMinutes;

    @Value("${cache.caffeine.maximumSize}")
    private long maximumSize;

    public static final String DATA_MODEL_CACHE_MANAGER_NAME = "plant-catalog.data-model";
    public static final String PLANT_CLASS_CACHE_MANAGER_NAME = "plant-catalog.plant-class";

    @Bean
    public CacheManager simpleCacheManager() {
        return new ConcurrentMapCacheManager(DATA_MODEL_CACHE_MANAGER_NAME);
    }

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder().maximumSize(maximumSize).expireAfterWrite(expireAfterWriteDurationInMinutes, TimeUnit.MINUTES);
    }

    @Bean

    public CacheManager caffeineCacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager(PLANT_CLASS_CACHE_MANAGER_NAME);
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
