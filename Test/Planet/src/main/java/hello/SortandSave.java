package hello;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SortandSave {

	public void sortListPlanets(List<Planet> planets) throws SQLException{
		PlanetComparatorClass comparator = new PlanetComparatorClass();
		Collections.sort(planets, comparator);
		savetoDataBase(planets);
	}

	private void savetoDataBase(List<Planet> planets) throws SQLException {
		// TODO Auto-generated method stub
		try {
			  
			Connection conn= null;
			  
			  PreparedStatement prepStmt = conn.prepareStatement(    
			    "insert into planets(id,name,rotation_period,diameter) values (?,?,?,?");
			  Iterator<Planet> it = planets.iterator();
			  while(it.hasNext()){
			     Planet p = it.next();
			    prepStmt.setInt(1,p.getId());            
			    prepStmt.setString(2,p.getName());
			    prepStmt.setString(3,p.getRotation_period());
			    prepStmt.setString(4,p.getDiameter());
			    prepStmt.addBatch();                      

			  }      
			  int [] numUpdates=prepStmt.executeBatch();
			  for (int i=0; i < numUpdates.length; i++) {
			    if (numUpdates[i] == -2)
			      System.out.println("Execution " + i + 
			        ": unknown number of rows updated");
			    else
			      System.out.println("Execution " + i + 
			        "successful: " + numUpdates[i] + " rows updated");
			  }
			  conn.commit();
			} catch(BatchUpdateException b) {
			  // process BatchUpdateException
			} 
	}
}
