package dev.enginecode.weeds.plantcareservice.plantcatalog


import dev.enginecode.weeds.plantcareservice.intestspec.IntestSpecification
import dev.enginecode.weeds.plantcareservice.plantcatalog.presentation.model.PlantClassView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.http.HttpStatus
import org.springframework.test.annotation.DirtiesContext

import java.util.stream.IntStream

import static dev.enginecode.weeds.plantcareservice.plantcatalog.infrastructure.InfrastructureConfiguration.PLANT_CLASS_CACHE_MANAGER_NAME

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GetPlantClassViewCacheTest extends IntestSpecification {

    private static final def PLANT_ID_1 = "6ea8fed8-2d86-4e0c-8593-d33314a86747"
    private static final def PLANT_ID_2 = "ccabd618-b415-4457-b355-aada204f6b8c"
    private static final def PLANT_ID_3 = "0a0279a1-6182-4ad6-ae81-fd783528ac18"
    private static final def PLANT_ID_4 = "e8e31639-bd65-46af-a594-fa59aa510bdf"

    @Autowired
    private CacheManager cacheManager


    def setup() {
        dbAdmin.runSqlFile("db/runtime-sql/plant-classes-dml.sql")
    }

    def "should get plant class with the given id from cache"() {
        given:
        def cache = cacheManager.getCache(PLANT_CLASS_CACHE_MANAGER_NAME)
        expect:
        cache.get(PLANT_ID_1) == null

        when:
        def responseEntity = getPlantClassViewByID(PLANT_ID_1)
        then:
        responseEntity.getStatusCode() == HttpStatus.OK

        when:
        def plantClassViewFromCache = cache.get(UUID.fromString(PLANT_ID_1))?.get() as PlantClassView

        then:
        plantClassViewFromCache.id() == UUID.fromString(PLANT_ID_1)
    }

    def "should evict least frequently used object from cache"() {
        given:
        def cache = cacheManager.getCache(PLANT_CLASS_CACHE_MANAGER_NAME)
        expect:
        cache.get(PLANT_ID_1) == null

        when:
        IntStream.range(0, 5).forEach { getPlantClassViewByID(PLANT_ID_1) }
        getPlantClassViewByID(PLANT_ID_2)
        IntStream.range(0, 12).forEach { getPlantClassViewByID(PLANT_ID_3) }

        def plantClassView1 = cache.get(UUID.fromString(PLANT_ID_1))?.get() as PlantClassView
        def plantClassView2 = cache.get(UUID.fromString(PLANT_ID_2))?.get() as PlantClassView
        def plantClassView3 = cache.get(UUID.fromString(PLANT_ID_3))?.get() as PlantClassView
        def plantClassView4 = cache.get(UUID.fromString(PLANT_ID_4))?.get() as PlantClassView

        then:
        plantClassView1.id() == UUID.fromString(PLANT_ID_1)
        plantClassView2 == null
        plantClassView3.id() == UUID.fromString(PLANT_ID_3)
        plantClassView4 == null

    }


    private def getPlantClassViewByID(String id) {
        return testRestTemplate.getForEntity(
                "http://localhost:$port/plantcare-service/plant-classes/$id",
                String.class
        )
    }
}

