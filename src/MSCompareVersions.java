public class MSCompareVersions {

    public static void main(String[] args) {
        System.out.println(compareVersion("0.1", "1.1"));
        System.out.println(compareVersion("1", "1.1"));
    }

    public static int compareVersion(String version1, String version2) {
        String[] ver1Splitted = version1.split("\\.");
        String[] ver2Splitted = version2.split("\\.");
        int[] ver1SInt = new int[ver1Splitted.length];
        for (int i = 0; i < ver1Splitted.length; i++) {
            ver1SInt[i] = Integer.valueOf(ver1Splitted[i]);
        }
        int[] ver2SInt = new int[ver2Splitted.length];
        for (int i = 0; i < ver2Splitted.length; i++) {
            ver2SInt[i] = Integer.valueOf(ver2Splitted[i]);
        }
        int minIndex = Math.min(ver1Splitted.length, ver2Splitted.length);
        int index = 0;
        int result = 0;
        while (result == 0 && index <= minIndex - 1) {
            if (ver1SInt[index] > ver2SInt[index]) {
                return 1;
            } else if (ver1SInt[index] < ver2SInt[index]) {
                return -1;
            }
            index++;
        }
        if (ver1Splitted.length > ver2Splitted.length) {
            while (index <= ver1Splitted.length - 1) {
                if (ver1SInt[index] > 0) {
                    return 1;
                }
                index++;
            }
        } else if (ver1Splitted.length < ver2Splitted.length) {
            while (index <= ver2Splitted.length - 1) {
                if (ver2SInt[index] > 0) {
                    return -1;
                }
                index++;
            }
        }
        return result;
    }

}
