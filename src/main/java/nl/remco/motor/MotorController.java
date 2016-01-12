package nl.remco.motor;

import com.spotify.apollo.Environment;
import com.spotify.apollo.httpservice.HttpService;
import com.spotify.apollo.route.Route;

public final class MotorController {

	/*
	 * Pelco-D examples from http://www.commfront.com/RS232_Examples/CCTV/Pelco_D_Pelco_P_Examples_Tutorial.HTM
	 */
    private static final String GO_LEFT  = "FF0100043F0044";
    private static final String GO_RIGHT = "FF0100023F0042";
    private static final String STOP     = "FF010000000001";

    private static RS232 protocol;
 
    public static void main(String[] args) throws Exception {
    	protocol = new RS232("/dev/tty.usbserial-AI02MG49");
        HttpService.boot(MotorController::init, "MotorController", args);
    }

    static void init(Environment environment) {
        environment.routingEngine()
        	.registerAutoRoute(Route.sync("GET", "/", rc -> "hello world"));
        environment.routingEngine()
        	.registerAutoRoute(Route.sync("GET", "/left", rc -> sendCommand(GO_LEFT)));
        environment.routingEngine()
    		.registerAutoRoute(Route.sync("GET", "/right", rc -> sendCommand(GO_RIGHT)));
        environment.routingEngine()
    		.registerAutoRoute(Route.sync("GET", "/stop", rc -> sendCommand(STOP)));
    }

    static boolean sendCommand(String cmd) {
    	try {
    		return protocol.sendHex(cmd);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
}