import java.util.stream.IntStream;

public class Util {
    public static int ternarySearch(int[] arr, int l, int r, int key) {
        if( r < l) return -1;
        int mid1 = l + (r -l ) / 3;
        int mid2 = r - (r -l ) / 3;
        if(arr[mid1] == key)
            return mid1;
        if(arr[mid2] == key)
            return mid2;

        if(key < arr[mid1]) {
            return ternarySearch(arr, l, mid1 - 1, key);
        }
        else if(key > arr[mid2]) {
            return ternarySearch(arr, mid2 + 1, r, key);
        }
        else {
            return ternarySearch(arr, mid1 + 1, mid2 -1, key);
        }
    }

    public static void main(String[] args) {
        int[] ints = IntStream.range(0, 51).toArray();

        int i = ternarySearch(ints, 0, ints.length-1, 5);
        assert i == 5;
        i = ternarySearch(ints, 0, ints.length-1, 50);
        assert i == 50;
    }
}
