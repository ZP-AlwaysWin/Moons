import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BinarySearch {
    public static int searchIndex(int key, Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;
        if (arr[start] > key || arr[end] < key) {
            return -1;
        }

        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] > key) {
                end = mid - 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                while (mid - 1 >= 0 && arr[mid - 1] == key) {
                    mid--;
                }
                return mid;
            }
        }
        return -1;
    }

    public static Integer[] searchAllIndex(int key, Integer[] arr) {
        Integer[] result;
        if (arr == null || arr.length == 0) {
            return new Integer[0];
        }
        int start = 0;
        int end = arr.length - 1;
        if (arr[start] > key || arr[end] < key) {
            return new Integer[0];
        }

        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] > key) {
                end = mid - 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                int midL = mid;
                int midR = mid;
                int count = 1;

                while (midL - 1 >= 0 && arr[midL - 1] == key) {
                    count++;
                    midL--;
                }
                while (midR + 1 < arr.length && arr[midR + 1] == key) {
                    count++;
                    midR++;
                }
                result = new Integer[count];
                for (int i = 0, from = midL; i < count; i++, from++) {
                    result[i] = from;
                }
                return result;
            }
        }
        return new Integer[0];
    }

    public static void main(String[] args) {
        List<Integer[]> dataSet = new ArrayList<>();

        dataSet.add(new Integer[]{2, 3, 4, 5, 6, 8, 9, 9, 10, 12, 17, 17, 20, 20, 20, 21});
        dataSet.add(new Integer[]{2, 2, 2, 2, 3, 4, 5, 6, 8, 9, 9, 10, 12, 17, 17, 17,});
        dataSet.add(new Integer[]{2, 2, 2, 2, 3, 4, 5, 6, 8, 9, 9, 10, 12, 12, 12, 13, 13, 13, 14});
        dataSet.add(new Integer[]{2, 2, 2, 2, 3, 4, 5, 6, 8, 9, 9, 10, 12, 17, 17, 18, 18, 18,});
        dataSet.add(new Integer[]{1, 1, 2, 2, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7});
        dataSet.add(new Integer[]{1, 1, 2, 2, 3, 4, 5, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 12});

        int size = dataSet.size();
        if (size >= 1) {
            List<Integer> randomKey = new ArrayList<>(dataSet.size());
            Random random = new Random();
            dataSet.forEach(arr -> {
                int key = 0;
                if (arr != null && arr.length >= 1) {
                    key = arr[0] + random.nextInt(arr[arr.length - 1]);
                } else {
                    key = random.nextInt(10);
                }
                randomKey.add(key);

            });

            for (int i = 0; i < size; i++) {
                Integer[] arr = dataSet.get(i);
                int key = randomKey.get(i);
                System.out.println(String.format("findIndex    %5s in %s, pos = %s", key, Arrays.toString(arr), searchIndex(key, arr)));
                Integer[] resultAll = searchAllIndex(key, arr);
                System.out.println(String.format("findAllIndex %5s in %s, pos = %s", key, Arrays.toString(arr), Arrays.toString(resultAll)));
                System.out.println();
            }
        }
    }
}
