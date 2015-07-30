package TestBots;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import robocode.*;
import robocode.util.Utils;

public class TestScanner extends AdvancedRobot {
	double radarDir = 1;
	private static LinkedHashMap<String, HashMap<String, Object>> map = new LinkedHashMap<String, HashMap<String, Object>>(4, 0.75f, true);
	
	public void run() {
		while (true) {
			setTurnRadarRightRadians(radarDir * Double.POSITIVE_INFINITY);
			execute();
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		setAhead(100);
		String name = e.getName();
		Double absBearing = e.getBearingRadians() + getHeadingRadians();
		
		map.put(name, new HashMap<String, Object>());
		map.get(name).put("absBearing", absBearing);

		if (map.size() == getOthers()) {
			Entry<String, HashMap<String, Object>> i = map.entrySet().iterator().next();		
			absBearing	= (Double) i.getValue().get("absBearing");
			radarDir	= Utils.normalRelativeAngle(absBearing - getRadarHeadingRadians());
		}
	}
}
