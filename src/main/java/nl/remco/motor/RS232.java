package nl.remco.motor;

import jssc.SerialPort;

public class RS232 implements CommunicationProtocol {

	private String deviceURI;
    private SerialPort serialPort;

	RS232(String device) throws Exception {
		deviceURI = device;
		startRS232();
	}

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    private void startRS232() throws Exception {
		serialPort = new SerialPort(deviceURI);
		serialPort.openPort();
		serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    }

    private boolean isReady() {
		return serialPort.isOpened();
    }

    @Override
	public boolean send(String buffer) throws Exception {
		if (!isReady()) {
			startRS232();
		}
		return serialPort.writeBytes(buffer.getBytes());
	}

	@Override
	public boolean sendHex(String buffer) throws Exception {
		if (!isReady()) {
			startRS232();
		}
		return serialPort.writeBytes(hexStringToByteArray(buffer));
	}

}
