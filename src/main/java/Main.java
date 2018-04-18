import java.lang.management.ManagementFactory;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());// for jconsole


        Runtime runtime = Runtime.getRuntime();

        int size = 10000000;

        while (true) {
            System.gc();
            Thread.sleep(100);
            long mem = runtime.totalMemory() - runtime.freeMemory();

            Object[] array = new Object[size];

            long mem2 = runtime.totalMemory() - runtime.freeMemory();

            System.out.println("Reference size: " + (mem2 - mem) / size);

            System.out.println("New array of size: " + array.length + " created");
            for (int i = 0; i < size; i++) {
//                array[i] = new Integer(123);
                array[i] = new String(new char[1]);
//                array[i] = new String(""); //String pool
//                array[i] = new String(new char[0]); //without String pool
                //array[i] = new MyClass();
            }
            System.out.println("Created " + size + " objects.");

            long mem3 = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Object size: " + (mem3 - mem2) / size);

            Thread.sleep(1000);
        }

    }
}
