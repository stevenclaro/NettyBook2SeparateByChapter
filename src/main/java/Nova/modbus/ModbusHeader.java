package Nova.modbus;

public class ModbusHeader {

    private  int transactionIdentifier;
    private  int protocolIdentifier;
    private  int length;

    public void setTransactionIdentifier(int transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public void setProtocolIdentifier(int protocolIdentifier) {
        this.protocolIdentifier = protocolIdentifier;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setUnitIdentifier(short unitIdentifier) {
        this.unitIdentifier = unitIdentifier;
    }

    private  short unitIdentifier;

    public ModbusHeader(){

    }

    public ModbusHeader(int transactionIdentifier, int protocolIdentifier, int pduLength, short unitIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
        this.protocolIdentifier = protocolIdentifier;
        this.length = pduLength + 1; //+ unit identifier
        this.unitIdentifier = unitIdentifier;
    }

    public int getLength() {
        return length;
    }

    public int getProtocolIdentifier() {
        return protocolIdentifier;
    }

    public int getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public short getUnitIdentifier() {
        return unitIdentifier;
    }
}
