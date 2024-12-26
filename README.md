## Microservices Aggregator Design Pattern

The Microservices Aggregator pattern helps aggregate responses from multiple microservices into a single unified response, optimizing client-server interactions in scalable systems.

## Detailed Explanation of Microservices Aggregator Pattern with Real-World Examples

Real-world example

> In a travel booking platform, an Aggregator Microservice consolidates data from flights, hotels, and car rentals microservices, providing a seamless user experience and enhancing scalability. Instead of the user making separate requests to each service, the platform employs an Aggregator Microservice. This microservice calls each of these services, collects their responses, and then consolidates the information into a single, unified response that is sent back to the user. This simplifies the user experience by providing all necessary travel details in one place and reduces the number of direct interactions the user needs to have with the underlying services.

In plain words

> Microservices Aggregator collects pieces of data from various microservices and returns an aggregate for processing.

Stack Overflow says

> Microservices Aggregator invokes multiple services to achieve the functionality required by the application.

## Programmatic Example of Microservices Aggregator Pattern in Java

Our web marketplace utilizes an Aggregator microservice to fetch combined product and inventory information from separate microservices, ensuring efficient data processing and improved system performance.

Let's start from the data model. Here's our `Product`.

```java
public class Product {
    private String title;
    private int productInventories;
    // Other properties and methods...
}
```

Next we can introduce our `Aggregator` microservice. It contains clients `ProductInformationClient` and `ProductInventoryClient` for calling respective microservices.

```java

@RestController
public class Aggregator {

    @Resource
    private ProductInformationClient informationClient;

    @Resource
    private ProductInventoryClient inventoryClient;

    @RequestMapping(path = "/product", method = RequestMethod.GET)
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
```

Here's the essence of information microservice implementation. Inventory microservice is similar, it just returns inventory counts.

```java

@RestController
public class InformationController {
    @RequestMapping(value = "/information", method = RequestMethod.GET)
    public String getProductTitle() {
        return "The Product Title.";
    }
}
```

Now calling our `Aggregator` REST API returns the product information.

```bash
# Example bash call
curl http://localhost:50004/product

# Example output
{"title":"The Product Title.","productInventories":5}
```

## When to Use the Microservices Aggregator Pattern in Java

The Microservices Aggregator pattern is ideal for scenarios requiring composite responses from multiple microservices, such as in e-commerce and dashboard applications where aggregated data enhances user experience and system efficiency.
