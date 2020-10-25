package Nova.modbus;

public class ModbusFunction {
        public static final short READ_COILS = 0x01;
        public static final short READ_DISCRETE_INPUTS = 0x02;
        public static final short READ_HOLDING_REGISTERS = 0x03;
        public static final short READ_INPUT_REGISTERS = 0x04;
        public static final short WRITE_SINGLE_COIL = 0x05;
        public static final short WRITE_SINGLE_REGISTER = 0x06;
        public static final short WRITE_MULTIPLE_COILS = 0x0F;
        public static final short WRITE_MULTIPLE_REGISTERS = 0x10;
        public static final short READ_FILE_RECORD = 0x14;
        public static final short WRITE_FILE_RECORD = 0x15;
        public static final short MASK_WRITE_REGISTER = 0x16;
        public static final short READ_WRITE_MULTIPLE_REGISTERS = 0x17;
        public static final short READ_FIFO_QUEUE = 0x18;
        public static final short ENCAPSULATED_INTERFACE_TRANSPORT = 0x2B;
}
