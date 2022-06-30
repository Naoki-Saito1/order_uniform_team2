package util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateFormat {

	public String format(Timestamp t) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		
		return sdf.format(t);
	}

}

