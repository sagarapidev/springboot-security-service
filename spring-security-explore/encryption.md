# 🔐 File Encryption with OpenSSL

This guide explains how to encrypt a file using OpenSSL with AES-256-CBC encryption and PBKDF2 key derivation.

## 📄 Input File
- `plain.txt`: Contains the original plaintext data.

## 🔧 Encryption Command
```bash
openssl enc -aes-256-cbc -pbkdf2 -pass pass:12345 -in plain.txt -out plain2encrypt.txt -base64

## 🔒 Decryption Command

```bash
openssl enc -d -aes-256-cbc -pbkdf2 -pass pass:12345 -in plain2encrypt.txt -out enc2plain.txt -base64