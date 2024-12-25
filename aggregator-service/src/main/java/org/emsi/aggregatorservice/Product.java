package org.emsi.aggregatorservice;

import lombok.Getter;
import lombok.Setter;

/**
 * Encapsulates all the data for a Product that clients will request.
 */
@Getter
@Setter
public class Product {

    private String title;

    private int productInventories;

}
