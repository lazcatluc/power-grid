package ro.powergrid.plant;

import java.util.ArrayList;

import ro.powergrid.resource.Resource;

public class UpdateableResourceList extends ArrayList<Resource> {

	private static final long serialVersionUID = 6481040945419387259L;
	
	@Override
	public boolean add(Resource elem) {
		for (Resource previous : this) {
			if (previous.getResourceType().equals(elem.getResourceType())) {
				Resource newComposite = new Resource(previous.getValue()+elem.getValue(),
						previous.getResourceType());
				super.remove(previous);
				super.add(newComposite);
				return true;
			}
		}
		return super.add(elem);
	}

}
