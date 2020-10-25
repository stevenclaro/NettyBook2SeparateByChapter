package Nova.modbus;

public class ModbusFrame {
    private final ModbusHeader header;
    private final ModbusFunction function;
    private byte bytefunction;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    private byte[] data;
    public ModbusFrame(ModbusHeader header, ModbusFunction function) {
        this.header = header;
        this.function = function;
    }

    public ModbusHeader getHeader() {
        return header;
    }

    public byte getBytefunction() {
        return bytefunction;
    }

    public void setBytefunction(byte bytefunction) {
        this.bytefunction = bytefunction;
    }

    public ModbusFunction getFunction() {
        return function;

    }
}
