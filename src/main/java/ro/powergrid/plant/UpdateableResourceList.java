package ro.powergrid.plant;

import java.util.ArrayList;

import ro.powergrid.resource.Resource;

class UpdateableResourceList extends ArrayList<Resource> {

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
	
	@Override
	public boolean remove(Object elem) {
		if (!(elem instanceof Resource)) {
			return false;
		}
		
		int numberOfNecessaryResources = ((Resource)elem).getValue();
		while (!isEmpty()) {
	        final Resource nextResources = iterator().next();
	        super.remove(nextResources);
	        if (nextResources.getValue() > numberOfNecessaryResources) {
	            super.add(new Resource(nextResources.getValue()
	                    - numberOfNecessaryResources, nextResources.getResourceType()));
	            return true;
	        }
	        numberOfNecessaryResources -= nextResources.getValue();
	    }
		return false;
	}

}
