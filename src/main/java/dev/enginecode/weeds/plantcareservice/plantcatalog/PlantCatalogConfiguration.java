package dev.enginecode.weeds.plantcareservice.plantcatalog;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("dev.enginecode.eccommons.infrastructure.json.model")
@EnableJpaRepositories("dev.enginecode.eccommons.infrastructure.json.repository")
@ComponentScan({"dev.enginecode"})
@EnableCaching
public class PlantCatalogConfiguration {

    public static final String DATA_MODEL_CACHE_MANAGER_NAME = "plant-catalog.data-model";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(DATA_MODEL_CACHE_MANAGER_NAME);
    }

}
