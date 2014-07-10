package ro.powergrid.plant;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;

import ro.powergrid.resource.ResourceType;

public class PowerPlantJSONConfiguration implements Serializable, PowerPlantConfiguration {
	
	private static final long serialVersionUID = 1056380061804271166L;
	private final transient InputStream plantsStream;
	private final Map<Integer, PowerPlant> plants;
	
	public PowerPlantJSONConfiguration() {
		plantsStream = PowerPlantJSONConfiguration.class.getResourceAsStream("plants.json");
		plants = Collections.unmodifiableMap(getPlants(plantsStream));
	}
	
	/* (non-Javadoc)
	 * @see ro.powergrid.plant.PowerPlantConfiguration#getPlants()
	 */
	@Override
	public Collection<PowerPlant> getPlants() {
		return plants.values();
	}
	
	/* (non-Javadoc)
	 * @see ro.powergrid.plant.PowerPlantConfiguration#getPlant(java.lang.Integer)
	 */
	@Override
	public PowerPlant getPlant(Integer basePrice) {
		return plants.get(basePrice);
	}
	
	protected Map<Integer, PowerPlant> getPlants(InputStream is) {
		Map<Integer, PowerPlant> plants = new HashMap<>();
		JsonReader reader = Json.createReader(is);
		JsonArray plantsArray = reader.readArray();
		for (JsonValue jsonValue : plantsArray) {
			JsonObject plantObject = (JsonObject)jsonValue;
			PowerPlantBuilder builder = new PowerPlantBuilder()
				.withBasePrice(plantObject.getInt("basePrice"))
				.withNecessaryResources(plantObject.getInt("necessaryResources"))
				.withNumberOfCitiesPowered(plantObject.getInt("numberOfCitiesPowered"));
			JsonArray resources = plantObject.getJsonArray("resourceTypes");
			for (JsonValue resource : resources) {
				JsonString string = (JsonString)resource;
				ResourceType resourceType = ResourceType.valueOf(
						string.getString().toUpperCase());
				builder.withResourceType(resourceType);
			}
			PowerPlant plant = builder.build();
			plants.put(plant.getBasePrice(), plant);
		}
		return plants;
	}
}
