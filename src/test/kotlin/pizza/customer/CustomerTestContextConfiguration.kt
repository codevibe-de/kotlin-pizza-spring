package pizza.customer

import io.mockk.mockk
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import pizza.SampleDataLoader.SmallDataLoader
import pizza.SampleDataLoaderRunner
import pizza.product.ProductRepository

@TestConfiguration
@ComponentScan("pizza.product") // to get both service and repository beans
@Import(
    CustomerService::class,
    SampleDataLoaderRunner::class,
    SmallDataLoader::class,
    DataSourceAutoConfiguration::class
)
class CustomerTestContextConfiguration {
    // Demonstrates replacing an existing bean with regular bean definition mechanics.
    // This can be, of course, replaced by just a @MockBean declaration in the test class itself.
    @Bean("productJdbcDao")
    fun mockedProductRepository(): ProductRepository {
        return mockk<ProductRepository>(relaxed = true)
    }
}
