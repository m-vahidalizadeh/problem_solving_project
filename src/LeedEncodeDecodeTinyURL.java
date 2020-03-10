import java.util.Base64;

public class LeedEncodeDecodeTinyURL {

    public static void main(String[] args) {
        LeedEncodeDecodeTinyURL leedEncodeDecodeTinyURL = new LeedEncodeDecodeTinyURL();
        String shortUrl = leedEncodeDecodeTinyURL.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.format("The decoded String of short URL %s is %s", shortUrl, leedEncodeDecodeTinyURL.decode(shortUrl));
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        return Base64.getEncoder().encodeToString(longUrl.getBytes());
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return new String(Base64.getDecoder().decode(shortUrl.getBytes()));
    }

}
