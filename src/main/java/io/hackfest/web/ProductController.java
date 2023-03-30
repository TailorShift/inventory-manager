package io.hackfest.web;

import io.hackfest.db.AvailableItem;
import io.hackfest.db.InventoryMovementEntity;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.ResponseStatus;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import java.util.List;

@Path("/availableItems")
public class ProductController {

    @GET
    @Path("/{productId}")
    @ResponseStatus(200)
    public List<AvailableItem> getProduct(
            @PathParam("productId") Long productId
    ) {
        return InventoryMovementEntity.getAvailableItemsInAllShops(productId);
    }
}
