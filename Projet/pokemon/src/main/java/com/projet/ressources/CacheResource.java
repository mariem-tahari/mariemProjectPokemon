package com.projet.resources;

import com.projet.services.CacheService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;

@Path("/cache")
public class CacheResource {

    @Inject
    CacheService cacheService;

    @GET
    @Path("/afficher")
    public Map<Long, Object> afficherCache() {
        // Retourne le cache complet
        return cacheService.getAllCache();
    }
}
