import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SimpleHMAC {
    private static final int BLOCK_SIZE = 64; // Block size for MD5 and SHA-256
    private static final byte OPAD = 0x5c;    // Outer padding byte
    private static final byte IPAD = 0x36;    // Inner padding byte
    
    // Main method to run benchmarks
    public static void main(String[] args) {
        byte[] key = "secret_key".getBytes(StandardCharsets.UTF_8);
        
        // Test with different message sizes
        int[] sizes = {10, 100, 1000, 5000};
        for (int size : sizes) {
            byte[] message = generateRandomMessage(size);
            
            // Benchmark MD5 HMAC
            System.out.println("\n--- MD5 HMAC ---");
            long startMD5 = System.currentTimeMillis();
            String md5Mac = hmac(message, key, new MD5Hash());
            long endMD5 = System.currentTimeMillis();
            System.out.println("Size: " + size + " bytes | Time: " + 
                              ((endMD5 - startMD5) / 1000.0) + " s | MAC: " + 
                              md5Mac.substring(0, 16) + "...");
            
            // Benchmark SHA-256 HMAC
            System.out.println("\n--- SHA-256 HMAC ---");
            long startSHA = System.currentTimeMillis();
            String shaMac = hmac(message, key, new SHA256Hash());
            long endSHA = System.currentTimeMillis();
            System.out.println("Size: " + size + " bytes | Time: " + 
                              ((endSHA - startSHA) / 1000.0) + " s | MAC: " + 
                              shaMac.substring(0, 16) + "...");
        }
    }
    
    // HMAC algorithm implementation
    public static String hmac(byte[] message, byte[] key, HashFunction hash) {
        // Step 1: Process the key
        if (key.length > BLOCK_SIZE) {
            key = hash.digest(key);
        }
        
        if (key.length < BLOCK_SIZE) {
            byte[] newKey = new byte[BLOCK_SIZE];
            System.arraycopy(key, 0, newKey, 0, key.length);
            key = newKey;
        }
        
        // Step 2: Create padded keys
        byte[] outerKey = new byte[BLOCK_SIZE];
        byte[] innerKey = new byte[BLOCK_SIZE];
        
        for (int i = 0; i < BLOCK_SIZE; i++) {
            outerKey[i] = (byte)(key[i] ^ OPAD);
            innerKey[i] = (byte)(key[i] ^ IPAD);
        }
        
        // Step 3: Compute inner hash
        byte[] innerData = concat(innerKey, message);
        byte[] innerHash = hash.digest(innerData);
        
        // Step 4: Compute outer hash
        byte[] outerData = concat(outerKey, innerHash);
        byte[] result = hash.digest(outerData);
        
        // Convert to hex string
        return toHex(result);
    }
    
    // Helper method to concatenate byte arrays
    private static byte[] concat(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }
    
    // Helper method to convert bytes to hex string
    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
    
    // Generate random message of specified size
    private static byte[] generateRandomMessage(int size) {
        byte[] message = new byte[size];
        for (int i = 0; i < size; i++) {
            message[i] = (byte)('a' + (i % 26));
        }
        return message;
    }
    
    // Interface for hash functions
    interface HashFunction {
        byte[] digest(byte[] input);
    }
    
    // Simplified MD5 implementation (in practice, use java.security.MessageDigest)
    static class MD5Hash implements HashFunction {
        @Override
        public byte[] digest(byte[] input) {
            // In a real exam, you would use Java's built-in library
            // This is a placeholder - in actual exam code:
            try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
                return md.digest(input);
            } catch (Exception e) {
                return new byte[16]; // Return empty hash on error
            }
        }
    }
    
    // Simplified SHA-256 implementation (in practice, use java.security.MessageDigest)
    static class SHA256Hash implements HashFunction {
        @Override
        public byte[] digest(byte[] input) {
            // In a real exam, you would use Java's built-in library
            // This is a placeholder - in actual exam code:
            try {
                java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
                return md.digest(input);
            } catch (Exception e) {
                return new byte[32]; // Return empty hash on error
            }
        }
    }
}