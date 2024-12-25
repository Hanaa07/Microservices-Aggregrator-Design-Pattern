package org.emsi.informationservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InformationController {

    /**
     * Endpoint to retrieve a product's information.
     *
     * @return product inventory.
     */
    @GetMapping("/information")
    public String getProductTitle() {
        return "The Product Title.";
    }
}
