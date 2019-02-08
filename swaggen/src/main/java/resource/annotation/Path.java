package resource.annotation;

import java.util.Map;

import enums.RequestMethod;

/**
 * A collection of all Endpoints belonging to a single URL
 * 
 * @author William Gardiner (7267012)
 */
public class Path {
	
	/**
	 * The URL
	 */
	private String path;
	
	/**
	 * The Endpoints
	 */
	private Map<RequestMethod, Endpoint> endpoints;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<RequestMethod, Endpoint> getEndpoints() {
		return endpoints;
	}

	public void setEndpoints(Map<RequestMethod, Endpoint> endpoints) {
		this.endpoints = endpoints;
	}

	@Override
	public String toString() {
		return "Path [path=" + path + ", endpoints=" + endpoints + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Path other = (Path) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}	
}
