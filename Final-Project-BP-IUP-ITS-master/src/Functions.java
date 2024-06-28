public final class Functions {
    public static void printLoop(String input, int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.out.print(input);
        }
    }

    public static int getDigits10(int input) {
        return (int) Math.log10(input) + 1;
    }

    // chiyoung part
    public static int randomInt(int[] exc){
        int res = (int) (Math.random()*6+1);
        while(contains(exc,res) != -1){
            res = (int) (Math.random()*6+1);
        }
        return res;
    }

    // chiyoung part
    public static int contains(int[] array, int key) {
        int count = 0;
        for (int i : array) {
            if (i == key) {
                return count;
            }
            count++;
        }
        return -1;
    }

    // chiyoung part
    public static int maxList(int[] L){
        int n = L.length;
        int max = L[0];
        int index = 0;
        for(int i = 1; i < n; i++)
        {
            if(L[i] > max) {
                max = L[i];
                index = i;
            }
        }
        return index;
    }

    public static void wait(int amount) {
        try {
            Thread.sleep(amount);
        } catch(InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
