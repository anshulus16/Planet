package hello;

import java.util.Comparator;

public class PlanetComparatorClass implements Comparator<Planet> {
	  @Override
	  public int compare(Planet x, Planet y) {
	    // TODO: Handle null x or y values
	    int startComparison = compare(x.getId(), y.getId());
	    return startComparison != 0 ? startComparison
	                                : compare(x.getId(), y.getId());
	  }

	  
	  private static int compare(long a, long b) {
	    return a < b ? -1
	         : a > b ? 1
	         : 0;
	  }
	}

