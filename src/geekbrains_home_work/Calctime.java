package geekbrains_home_work;

public class Calctime {
    private static final int size = 10000000;
    private static final int h = size / 2;

    public float[] calculate(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + arr[i] / 5) * Math.cos(0.2f + arr[i] / 5) * Math.cos(0.4f + arr[i] / 2));
        return arr;
    }

    public void CalcTimeOne() {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;
        long a = System.currentTimeMillis();
        calculate(arr);
        System.out.println("Время заполнения еденицами первого метода: " + (System.currentTimeMillis() - a));
    }

    public void CalcTimeTwo() {
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        for (int i = 0; i < arr.length; i++) arr[i] = 1.0f;

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        new Thread() {
            public void run() {
                float[] ar1 = calculate(a1);
                System.arraycopy(ar1, 0, a1, 0, ar1.length);
            }
        }.start();

        new Thread() {
            public void run() {
                float[] ar2 = calculate(a2);
                System.arraycopy(ar2, 0, a2, 0, ar2.length);
            }
        }.start();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println("Время заполнения еденицами второго метода: " + (System.currentTimeMillis() - a));

       }

}
