package com.libraryapp.springbootlibrary.utils;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ExtractJWT {
    /**
     * ALl of this is to split the token into three parts, header, payload, signiture
     * decode the token
     * search for "sub" in the payload
     * and return the value of "sub" to dynamically retrieve the email info
     *
     * @param token
     * @return
     */
    public static String payloadJWTExtraction (String token, String extraction) {

        // Remove "Bearer " from the beginning of the token.
        token.replace("Bearer ", "");

        // Split the token.
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payload = new String(decoder.decode(chunks[1]));

        String[] entries = payload.split(",");

        // We want a key value pair
        Map<String, String> map = new HashMap<String, String>();

        // Looping through all the entries until we find the "sub" entry.
        for (String entry : entries) {
            String[] keyValue = entry.split(":");
            if (keyValue[0].equals(extraction)) {

                int remove = 1;
                if (keyValue[1].endsWith("}")) {

                    remove = 2;
                }

                keyValue[1] = keyValue[1].substring(0, keyValue[1].length() - remove);
                keyValue[1] = keyValue[1].substring(1);

                // Just want to add the value which *should* be the email.
                map.put(keyValue[0], keyValue[1]);
            }

        }

        // If the key is found, then return the value which is the email.
        if (map.containsKey(extraction)) {
            return map.get(extraction);
        }

        // If nothing is found.
        return null;
    }
}
