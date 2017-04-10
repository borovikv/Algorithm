package DataStructure;

import org.jetbrains.annotations.Contract;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by vborovic on 4/10/17.
 */
@SuppressWarnings("WeakerAccess")
public class Hash<K, V> {
    private final HashType type;
    @SuppressWarnings("unchecked")
    Item[] storage = (Item[]) Array.newInstance(Item.class, 11);
    Item noItem = new Item(null, null);

    Hash(HashType type) {
        this.type = type;
        type.setStepSize(storage.length);
    }

    public void insert(K key, V value) {
        int start = hash(key);
        int hash = start;
        type.resetI();
        while (storage[hash] != null && storage[hash] != noItem && storage[hash].key != key) {
            hash = type.next_key(hash, storage.length);
            if (hash == start) {
                throw new RuntimeException("Overflow");
            }
        }
        Item item = new Item(key, value);
        storage[hash] = item;
    }

    public void delete(K key) {
        int start = hash(key);
        int hash = start;
        int keyHash = key.hashCode();
        type.resetI();
        while (storage[hash] != null) {
            if (storage[hash].keyHash == keyHash) {
                storage[hash] = noItem;
                return;
            }
            hash = type.next_key(hash, storage.length);
            if (hash == start) {
                return;
            }
        }
    }

    public V get(@SuppressWarnings("SameParameterValue") K key) {
        Item item = find(key);
        if (item == null || item == noItem) {
            return null;
        }
        return item.value;
    }

    private Item find(K key) {
        int hash = hash(key);
        int keyHash = key.hashCode();
        type.resetI();
        while (storage[hash] != null) {
            if (storage[hash].keyHash == keyHash) {
                return storage[hash];
            }
            hash = type.next_key(hash, storage.length);
        }
        return null;
    }

    @Contract(pure = true)
    private int hash(K key) {
        return Math.abs(key.hashCode() % storage.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(storage);
    }

    class Item {
        K key;
        V value;
        int keyHash;

        Item(K key, V value) {
            this.key = key;
            this.value = value;
            if (key == null) {
                keyHash = -1;
            } else {
                keyHash = key.hashCode();
            }
        }

        @Override
        public String toString() {
            return "key: " + key + ", value: " + value + ", hash: " + keyHash;
        }
    }

    enum HashType {
        Linear, Quad, Double;

        int stepSize;
        int i = 0;

        void setStepSize(int length) {
            for(int i=length-1; i > 1; i--) {
                if (isPrime(i)) {
                    stepSize = i;
                }
            }
        }

        boolean isPrime(int n) {
            for (int i=2; i*i < n; n++) if (n % i == 0) return false;
            return true;
        }

        public void resetI() {
            this.i = 0;
        }

        int next_key(int key, int length) {
            switch (name()) {
                case "Linear":
                    return (key + 1) % length;
                case "Quad":
                    i++;
                    return (int) ((key + Math.pow(i, 2)) % length);
                case "Double":
                    return (key + stepSize) % length;
                default:
                    throw new RuntimeException();
            }
        }
    }

    public static void main(String[] args) {
        Hash<String, Integer> hash = new Hash<>(HashType.Double);
        hash.insert("one", 1);
        hash.insert("two", 2);
        hash.insert("three", 3);
        hash.insert("three1", 3);
        hash.insert("three2", 3);
        hash.insert("three3", 3);
        hash.insert("three4", 3);
        hash.insert("three5", 3);
        hash.insert("three6", 3);
        hash.insert("three7", 3);
        hash.insert("three8", 3);
        System.out.println(hash);

        System.out.println(hash.get("two"));

        hash.delete("three");
        hash.delete("four");
        System.out.println(hash);
    }
}
