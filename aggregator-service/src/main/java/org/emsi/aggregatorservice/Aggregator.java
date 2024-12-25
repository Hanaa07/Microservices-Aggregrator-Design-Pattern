package org.emsi.aggregatorservice;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.requireNonNullElse;

/**
 * The aggregator aggregates calls on various micro-services, collects data and further publishes
 * them under a REST endpoint.
 */
@RestController
public class Aggregator {

    @Resource
    private ProductInformationClient informationClient;

    @Resource
    private ProductInventoryClient inventoryClient;

    /**
     * Retrieves product data.
     *
     * @return a Product.
     */
    @GetMapping("/product")
    public Product getProduct() {

        var product = new Product();
        var productTitle = informationClient.getProductTitle();
        var productInventory = inventoryClient.getProductInventories();

        //Fallback to error message
        product.setTitle(requireNonNullElse(productTitle, "Error: Fetching Product Title Failed"));

        //Fallback to default error inventory
        product.setProductInventories(requireNonNullElse(productInventory, -1));

        return product;
    }

}
