# 🔐 MAC Generation using SHA-128 (MD5) and SHA-256
Computes Message Authentication Codes (MACs) for messages of varying sizes using SHA-128 and SHA-256, and benchmarks time performance.

---

### Author: Shaina

---

## 🧪 Aim
To compute the Message Authentication Code (MAC) for messages of varying lengths using SHA-128 (implemented using MD5) and SHA-256, and to measure the time consumed for both algorithms.

---

## ⚙️ Procedure
1. Accept a secret key and a variable-sized input message.
2. Manually implement HMAC using the standard formula:
   ```text
   MAC = H((key ⊕ opad) + H((key ⊕ ipad) + message))
   ```
3. Use custom implementations:
MD5 for SHA-128
hashlib.sha256 logic replicated for SHA-256
4. Generate random messages of sizes [10, 100, 1000, 5000, 10000, 50000] bytes.
5. Benchmark and record time taken for MAC generation for each algorithm.

---

## 🧾 Code Overview
CustomHMAC.java: Contains logic to:
1. Generate random messages
2. Compute HMACs using custom MD5 and SHA-256 classes
3. Benchmark MAC generation time

MD5.java: Manual implementation of the MD5 hash function.

SHA256.java: Manual implementation of SHA-256 hash function.

---

## 📊 Sample Output
```yaml
--- Benchmarking MD5 (128-bit) ---
Message Size: 10 bytes | Time: 0.0 s | MAC: a5d5c3ef...
Message Size: 100 bytes | Time: 0.001 s | MAC: 74b1c1a2...
...

--- Benchmarking SHA-256 ---
Message Size: 10 bytes | Time: 0.001 s | MAC: 9a1d3b7c...
Message Size: 100 bytes | Time: 0.002 s | MAC: bc4e8d6a...
...
```

---

## 🔍 Observation
SHA-256 consistently takes slightly more time than SHA-128 (MD5) but provides better cryptographic strength and security.

---

## ✅ Result
MACs were successfully generated and time-benchmarked for different message sizes using both SHA-128 and SHA-256.

---

## 📁 How to Run
Compile the Java file:
``` bash
javac CustomHMAC.java
```
Run the program:
``` bash
java CustomHMAC
```

---
