package nl.remco.motor;

public interface CommunicationProtocol {

	boolean send(String buffer) throws Exception;
	boolean sendHex(String buffer) throws Exception;
}
