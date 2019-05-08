package my.sample;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyWriter {
    public void writePrivateKey(PrivateKey pvt, String outDir, String name) throws IOException {
        FileOutputStream out = new FileOutputStream(outDir + name +".key");
        out.write(pvt.getEncoded());
        out.close();
    }

    public void write(KeyPair keyPair, String outDir) throws IOException {
        writePrivateKey(keyPair.getPrivate(), outDir, "private");
        writePublicKey(keyPair.getPublic(), outDir, "public");
    }

    public void writePublicKey(PublicKey publicKey, String outDir, String name) throws IOException {
        FileOutputStream out = new FileOutputStream(outDir + name + ".pub");
        out.write(publicKey.getEncoded());
        out.close();
    }
}
