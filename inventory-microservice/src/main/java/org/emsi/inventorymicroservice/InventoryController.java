package org.emsi.inventorymicroservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {

    /**
     * Endpoint to retrieve a product's inventories.
     *
     * @return product inventory.
     */
    @GetMapping("/inventories")
    public int getProductInventories() {
        return 5;
    }

}
