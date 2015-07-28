/* 
 * TO DO
 * Turn radar shortest distance to centre of map
 * Set up radar for single enemy
 * For Melee battles target lock my opponent but occasionally sweep the field
 */

package mo.Radar;

import java.util.HashMap;
import java.util.Map.Entry;
import mo.Data.Data;
import robocode.util.Utils;

public class Radar {

	// VARIABLES
	private static double radarDir = 1;
	private static Entry<String, HashMap<String, Object>> radarTarget;

	// METHODS
	public static double getRadarDir() {
		if (Data.getMap().size() == Data.getBotNum() && Data.getBotNum() != 1) {
			radarTarget = Data.getMap().entrySet().iterator().next();
			radarDir = Utils.normalRelativeAngle((Double) radarTarget.getValue().get("absBearing") - Data.getMyRadarHeading());
		}
		return radarDir;
	}
}
