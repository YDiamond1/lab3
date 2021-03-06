import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    //parameters
    public static int sizeLRS1 = 64;
    public static int sizeLRS2 = 67;
    public static int sizeLRS3 = 79;

    public static int[] LRSIndexes1 = {4, 3, 1, 0};
    public static int[] LRSIndexes2 = {5, 2, 1, 0};
    public static int[] LRSIndexes3 = {4, 3, 2, 0};

    public static long initValue1 = 20348572384L;
    public static long initValue2 = 10182731234L;
    public static long initValue3 = 90823457123L;

    public static Queue<Integer> LRS1 = new LinkedList<>();
    public static Queue<Integer> LRS2 = new LinkedList<>();
    public static Queue<Integer> LRS3 = new LinkedList<>();

    public static void initLRSs(){
        initLRS(LRS1, initValue1, sizeLRS1);
        initLRS(LRS2, initValue2, sizeLRS2);
        initLRS(LRS3, initValue3, sizeLRS3);
    }

    public static void initLRS(Queue<Integer> queue, long initValue, int size){
        for(int i = 0; i < size; i++){
            queue.add((int)(initValue % 2));
            initValue/=2;
        }
    }

    public static void pushToQueue(Queue<Integer> LRS, int[] indexes){
        LinkedList<Integer> tmp = (LinkedList<Integer>) LRS;
        int xor = tmp.get(indexes[0]);
        for(int i = 1; i < indexes.length; i++){
            xor ^= tmp.get(indexes[i]);
        }
        LRS.add(xor);
    }

    public static int getGammaBit(){
        Integer value1 = LRS1.poll();
        Integer value2 = LRS2.poll();
        Integer value3 = LRS3.poll();
        pushToQueue(LRS1, LRSIndexes1);
        pushToQueue(LRS2, LRSIndexes2);
        pushToQueue(LRS3, LRSIndexes3);
        return value1 | value2 | value3;
    }
    public static byte getGamma(){
        byte tmp = 0;
        int i = 0;
        for(; i < 8; i++){
            tmp = (byte)(tmp * 2 + (byte)getGammaBit() & 0x01);
        }
        return tmp;
    }
    public static byte encode(byte open){
        return (byte)(open ^ getGamma());
    }

    public static Byte[] readText(BufferedInputStream in) throws IOException {
        List<Byte> bytes = new LinkedList<>();
        int error = in.read();
        while(error != -1){
            bytes.add((byte) error);
            error = in.read();
        }
        return bytes.toArray(new Byte[0]);
    }

    public static void main(String[] args) {
        initLRSs();
        try(BufferedInputStream in = new BufferedInputStream(System.in)){

            Byte[] x = readText(in);
            byte[] bytes = new byte[x.length];
            for (Byte val :
                    x) {
                bytes[]
            }
            System.out.println(new String(x));
            
            byte[] result = new byte[bytes.length];

            for(int i = 0; i < bytes.length; i++){
                result[i] = encode(bytes[i]);
            }
            String sresult = new String(result);
            System.out.println(sresult);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
