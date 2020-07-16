package com.android.framekit.utils

import com.android.framekit.extensions.bytes2HexString
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object EncryptUtil {

    fun encryptMD5ToString(data: String): String {
        return if (data.isEmpty()) {
            ""
        } else {
            encryptMD5ToString(data.toByteArray())
        }
    }

    fun encryptMD5ToString(data: ByteArray): String {
        return if (data.isEmpty()) {
            ""
        } else {
            bytes2HexString(encryptMD5(data), true)
        }
    }

    fun encryptMD5(data: ByteArray): ByteArray? {
        return if (data.isEmpty()) {
            null
        } else {
            hashTemplate(data, "MD5")
        }
    }

    /**
     * hash digest for a byte data.
     * @param algorithm  the digest algorithm. eg:MD5
     */
    fun hashTemplate(data: ByteArray, algorithm: String?): ByteArray? {
        return if (data.isEmpty()) null else try {
            val md = MessageDigest.getInstance(algorithm)
            md.update(data)
            md.digest()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            null
        }
    }

}