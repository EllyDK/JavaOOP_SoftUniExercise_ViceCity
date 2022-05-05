package viceCity.repositories;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class GunRepository implements Repository<Gun> {
    private Map<String, Gun> models;

    public GunRepository() {
        models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Gun> getModels() {
        return null;
    }

    @Override
    public void add(Gun model) {
        if (!models.containsKey(model)){
            models.put(model.getName(), model);
        }
    }

    @Override
    public boolean remove(Gun model) {
        return models.remove(model.getName()) != null;
    }

    @Override
    public Gun find(String name) {
        return models.get(name);
    }
}
