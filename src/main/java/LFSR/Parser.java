package LFSR;

import java.util.List;

public class Parser {
    private static final int BYTE_SIZE = 8;

    public static String parseStringToBinary(List<Byte> bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes.size() < Controller.DISPLAY_MAX_SIZE * 2) {
            for (byte aByte : bytes) {
                stringBuilder.append(String.format("%8s",
                        Integer.toBinaryString(aByte & 0xFF)).replace(' ', '0')).append("  ");
            }
        } else {
            stringBuilder.append("Первые 10 байт:\n");
            for (int i = 0; i < Controller.DISPLAY_MAX_SIZE; i++) {
                stringBuilder.append(String.format("%8s",
                        Integer.toBinaryString(bytes.get(i) & 0xFF)).replace(' ', '0')).append("  ");
            }
            stringBuilder.append("\nПоследние 10 байт:\n");
            for (int i = bytes.size() - Controller.DISPLAY_MAX_SIZE; i < bytes.size(); i++) {
                stringBuilder.append(String.format("%8s",
                        Integer.toBinaryString(bytes.get(i) & 0xFF)).replace(' ', '0')).append("  ");
            }
        }
        return stringBuilder.toString();
    }

    public static byte[] parseBinaryToBitArray(String binary) {
        byte[] bits = new byte[binary.length()];
        for (int i = 0; i < bits.length; i++) {
            bits[i] = (byte) (binary.charAt(i) == '0' ? 0 : 1);
        }
        return bits;
    }

    public static String parseKeyString(List<Byte> bytes) {
        if (bytes.size() < Controller.DISPLAY_MAX_SIZE * 2) {
            return parseStringToBinary(bytes);
        }
        return "Первые 10 байт:\n" +
                parseStringToBinary(bytes.subList(0, 10)) +
                "\nПоследние 10 байт:\n" +
                parseStringToBinary(bytes.subList(10, 20));
    }
}
