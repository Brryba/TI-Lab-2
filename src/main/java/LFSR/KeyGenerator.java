package LFSR;

public class KeyGenerator {
    public static byte[] keyBitsArray;
    public static final int REGISTER_LENGTH = Controller.REGISTER_LENGTH;
    public static final int BYTE_SIZE = 8;
    public static final int[] POSITIONS = {2, 35};

    public KeyGenerator(byte[] keyBitsArray) {
        KeyGenerator.keyBitsArray = keyBitsArray;
    }

    private static byte generateNextBit(int position) {
        byte next = keyBitsArray[POSITIONS[0] - 1];
        for (int i = 1; i < POSITIONS.length; i++) {
            next ^= keyBitsArray[POSITIONS[i] - 1];
        }
        return next;
    }

    public static byte generateByteKey() {
        byte result = 0;
        for (int i = 0; i < BYTE_SIZE; i++) {
            byte next = generateNextBit(i);
            result <<= 1;
            result += (keyBitsArray[keyBitsArray.length - 1]);
            for (int j = keyBitsArray.length - 1; j > 0; j--) {
                keyBitsArray[j] = keyBitsArray[j - 1];
            }
            keyBitsArray[0] = next;
        }
        return result;
    }
}