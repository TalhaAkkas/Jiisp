package components;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseAction {
	public void doAction(ResultSet rs) throws SQLException;
}
