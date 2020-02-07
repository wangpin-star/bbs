package com.wangpin.bbs.utils;

public class HexTest {

    private static final char HexCharArr[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    private static final String HexStr = "0123456789abcdef";

    public static String byteArrToHex(byte[] btArr) {
        char strArr[] = new char[btArr.length * 2];
        int i = 0;
        for (byte bt : btArr) {
            strArr[i++] = HexCharArr[bt>>>4 & 0xf];//得到低4位
            strArr[i++] = HexCharArr[bt & 0xf];//byte呢是一个字节，也就是8位，如：0010 0100
            //而0xf0呢，也是8位：1111 0000,按位与就可以得到高4位
        }
        return new String(strArr);
    }

    public static byte[] hexToByteArr(String hexStr) {
        char[] charArr = hexStr.toCharArray();
        byte btArr[] = new byte[charArr.length / 2];
        int index = 0;
        for (int i = 0; i < charArr.length; i++) {
            int highBit = HexStr.indexOf(charArr[i]);
            int lowBit = HexStr.indexOf(charArr[++i]);
            btArr[index] = (byte) (highBit << 4 | lowBit);
            index++;
        }
        return btArr;
    }
}